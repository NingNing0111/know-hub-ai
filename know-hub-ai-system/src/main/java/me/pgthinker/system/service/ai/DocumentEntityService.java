package me.pgthinker.system.service.ai;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import me.pgthinker.system.controller.vo.DocumentVO;

/**
 * @Project: me.pgthinker.system.service.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 17:16
 * @Description:
 */
public interface DocumentEntityService {

	Page<DocumentVO> listDocuments(DocumentVO document);

	Boolean deleteKnowledgeFile(DocumentVO documentVO);

    String download(Long fileId, HttpServletResponse response);
}
