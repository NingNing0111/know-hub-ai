package me.pgthinker.system.model.entity.ai;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.pgthinker.core.pojo.BaseEntity;
import me.pgthinker.core.service.objectstore.StorageFile;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: me.pgthinker.system.model.entity
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 02:54
 * @Description:
 */
@Data
@TableName(value = "file_source", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
public class OriginFileResource extends BaseEntity implements StorageFile {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;

    private String fileName;

    private String path;

    private Boolean isImage;

    private String bucketName;

    private String objectName;

    private String contentType;

    private Long size;

    private String md5;

    // 文档里的图片名称列表
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> images = new ArrayList<>();
}
