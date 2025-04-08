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
    List<Media> fromResourceId(List<String> resourceIds);

    String uploadFile(MultipartFile file);

    String uploadFile(MultipartFile file, String knowledgeId);
}
