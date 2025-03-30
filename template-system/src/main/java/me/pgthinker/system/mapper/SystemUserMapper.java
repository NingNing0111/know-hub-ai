package me.pgthinker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.pgthinker.system.model.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pgthinker
 * @description 针对表【system_user(系统用户表)】的数据库操作Mapper
 * @createDate 2025-03-30 18:28:12
 * @Entity generator.domain.SystemUser
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {

	SystemUser getUserWithRolesAndPermissions(String name);

}
