package me.pgthinker.system.service.ai;

import org.springframework.ai.model.Media;

import java.util.List;

/**
 * @Project: me.pgthinker.system.service.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 03:33
 * @Description:
 */
public interface OriginFileResourceService {
    List<Media> fromResourceId(List<String> resourceIds);
}
