package me.pgthinker.core.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Project: me.pgthinker.entity
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/20 21:52
 * @Description:
 */
@Data
public class BaseEntity implements Serializable {

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField(value = "update_time")
	private Date updateTime;

	/**
	 * 是否删除
	 */
	@TableField(value = "deleted")
	private Boolean deleted;

	/**
	 * 创建人
	 */
	@TableField(value = "creator")
	private String creator;

	/**
	 * 更新人
	 */
	@TableField(value = "updater")
	private String updater;

}
