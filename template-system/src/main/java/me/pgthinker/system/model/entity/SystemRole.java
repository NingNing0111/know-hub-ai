package me.pgthinker.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.pgthinker.core.pojo.BaseEntity;

import java.io.Serial;

/**
 * @Project: me.pgthinker.system.model.entity
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 17:36
 * @Description:
 */
@Data
@TableName(value = "system_role")
@EqualsAndHashCode(callSuper = true)
public class SystemRole extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 角色名
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 角色描述
	 */
	@TableField(value = "description")
	private String description;

}
