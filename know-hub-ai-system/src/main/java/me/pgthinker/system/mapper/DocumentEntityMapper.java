package me.pgthinker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.pgthinker.system.controller.vo.KnowledgeFileVO;
import me.pgthinker.system.model.entity.ai.DocumentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pgthinker
 * @description 针对表【document_entity】的数据库操作Mapper
 * @createDate 2025-03-13 00:06:01
 * @Entity generator.domain.DocumentEntity
 */
@Mapper
public interface DocumentEntityMapper extends BaseMapper<DocumentEntity> {

    List<DocumentEntity> selectByBaseId(Long knowledgeId);
}
