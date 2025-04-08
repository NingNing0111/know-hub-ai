package me.pgthinker.system.service.ai;

import com.baomidou.mybatisplus.extension.service.IService;
import me.pgthinker.system.model.entity.ai.OriginFileResource;
import org.springframework.ai.model.Media;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Project: me.pgthinker.system.service.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 03:33
 * @Description:
 */
public interface OriginFileResourceService extends IService<OriginFileResource> {

	/**
	 * 根据id转换Media
	 * @param resourceIds
	 * @return
	 */
	List<Media> fromResourceId(List<String> resourceIds);

	/**
	 * 对话附件
	 * @param file
	 * @return
	 */
	String uploadFile(MultipartFile file);

	/**
	 * 知识库附件
	 * @param file
	 * @param knowledgeId
	 * @return
	 */
	Long uploadFile(MultipartFile file, String knowledgeId);

}
