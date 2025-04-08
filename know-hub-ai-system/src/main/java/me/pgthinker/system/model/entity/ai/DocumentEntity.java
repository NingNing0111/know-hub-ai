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
 * @Date: 2025/4/8 08:32
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "document_entity")
public class DocumentEntity extends BaseEntity {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     *
     */
    @TableField(value = "path")
    private String path;

    /**
     *
     */
    @TableField(value = "uploader")
    private Long uploader;

    /**
     *
     */
    @TableField(value = "base_id")
    private Object baseId;
}
