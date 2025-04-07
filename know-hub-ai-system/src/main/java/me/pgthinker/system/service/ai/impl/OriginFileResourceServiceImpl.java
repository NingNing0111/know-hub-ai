package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.mapper.OriginFileResourceMapper;
import me.pgthinker.system.model.entity.ai.OriginFileResource;
import me.pgthinker.system.service.ai.OriginFileResourceService;
import org.springframework.ai.model.Media;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author pgthinker
* @description 针对表【origin_file_source(存储原始文件资源的表)】的数据库操作Service实现
* @createDate 2025-04-08 04:47:02
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class OriginFileResourceServiceImpl extends ServiceImpl<OriginFileResourceMapper, OriginFileResource>
    implements OriginFileResourceService {

    @Override
    public List<Media> fromResourceId(List<String> resourceIds) {
        return List.of();
    }
}




