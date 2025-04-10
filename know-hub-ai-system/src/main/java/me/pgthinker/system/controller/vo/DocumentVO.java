package me.pgthinker.system.controller.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.pgthinker.core.pojo.PageParam;

import java.time.LocalDateTime;

/**
 * @Project: me.pgthinker.system.controller.vo
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 23:31
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentVO extends PageParam {

	private String knowledgeBaseId;

	private Long id;

	private String fileName;

	/**
	 * 下载路径
	 */
	private String path;

	/**
	 * 是否存储到了向量数据库中
	 */
	private Boolean isEmbedding;

	/**
	 * 知识库ID
	 */
	private String baseId;

	/**
	 * 知识库名称
	 */
	private String knowledgeBaseName;

	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 上传时间
	 */
	private LocalDateTime uploadTime;

}
