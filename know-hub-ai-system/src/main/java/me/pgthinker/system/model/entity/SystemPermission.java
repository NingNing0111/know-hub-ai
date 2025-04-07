package me.pgthinker.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.pgthinker.core.pojo.BaseEntity;

/**
 * @Project: me.pgthinker.system.model.entity
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 17:36
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_permission")
public class SystemPermission extends BaseEntity {

	/**
	 * 权限ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 权限名称
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 权限描述
	 */
	@TableField(value = "description")
	private String description;

}
