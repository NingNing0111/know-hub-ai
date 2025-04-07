package me.pgthinker.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.pgthinker.core.pojo.BaseEntity;

/**
 * 角色-权限关联表
 *
 * @TableName system_role_permission
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_role_permission")
@Data
public class SystemRolePermission extends BaseEntity implements Serializable {

	/**
	 *
	 */
	@TableId(value = "id")
	private Long id;

	/**
	 * 角色ID
	 */
	@TableField(value = "role_id")
	private Long roleId;

	/**
	 * 权限ID
	 */
	@TableField(value = "permission_id")
	private Long permissionId;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

}