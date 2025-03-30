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
 * 用户-角色关联表
 *
 * @TableName system_user_role
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_user_role")
@Data
public class SystemUserRole extends BaseEntity implements Serializable {

	/**
	 *
	 */
	@TableId(value = "id")
	private Long id;

	/**
	 * 用户ID
	 */
	@TableField(value = "user_id")
	private Long userId;

	/**
	 * 角色ID
	 */
	@TableField(value = "role_id")
	private Long roleId;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

}