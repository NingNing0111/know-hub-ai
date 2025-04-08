package me.pgthinker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.pgthinker.system.model.entity.ai.OriginFileResource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pgthinker
 * @description 针对表【origin_file_source(存储原始文件资源的表)】的数据库操作Mapper
 * @createDate 2025-04-08 04:47:02
 * @Entity generator.domain.OriginFileSource
 */
@Mapper
public interface OriginFileResourceMapper extends BaseMapper<OriginFileResource> {

}
