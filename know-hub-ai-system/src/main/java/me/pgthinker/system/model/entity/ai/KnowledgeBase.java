package me.pgthinker.system.model.entity.ai;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.pgthinker.core.pojo.BaseEntity;

/**
 * @Project: me.pgthinker.system.model.entity.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 07:51
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "knowledge_base")
public class KnowledgeBase extends BaseEntity {

	/**
	 *
	 */
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;

	/**
	 *
	 */
	@TableField(value = "name")
	private String name;

	/**
	 *
	 */
	@TableField(value = "description")
	private String description;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

}
