package me.pgthinker.system.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import me.pgthinker.system.utils.SecurityFrameworkUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @Project: me.pgthinker.config
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/13 13:08
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));

		// 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
		return interceptor;
	}

	@Bean
	public GlobalConfig globalConfig() {
		GlobalConfig globalConfig = new GlobalConfig();
		globalConfig.setMetaObjectHandler(metaObjectHandler());
		return globalConfig;
	}

	/**
	 * 自动填充实现
	 * @return
	 */
	@Bean
	public MetaObjectHandler metaObjectHandler() {
		return new MetaObjectHandler() {
			@Override
			public void insertFill(MetaObject metaObject) {
				boolean createTime = metaObject.hasSetter("createTime");
				boolean updateTime = metaObject.hasSetter("updateTime");
				boolean creator = metaObject.hasSetter("creator");
				boolean updater = metaObject.hasSetter("updater");
				if (createTime && updateTime) {
					this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
					this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
				}
				SecurityFrameworkUtil.tryGetLoginUser().ifPresent(user -> {
					if (creator) {
						this.setFieldValByName("creator", user.getId(), metaObject);
					}
					if (updater) {
						this.setFieldValByName("updater", user.getId(), metaObject);
					}
				});
			}

			@Override
			public void updateFill(MetaObject metaObject) {
				boolean updateTime = metaObject.hasSetter("updateTime");
				boolean updater = metaObject.hasSetter("updater");
				if (updateTime) {
					this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
				}
				SecurityFrameworkUtil.tryGetLoginUser().ifPresent(user -> {
					if (updater) {
						this.setFieldValByName("updater", user.getId(), metaObject);
					}
				});

			}
		};
	}

}
