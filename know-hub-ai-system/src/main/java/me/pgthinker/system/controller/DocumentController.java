package me.pgthinker.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import me.pgthinker.core.common.BaseResponse;
import me.pgthinker.core.common.ResultUtils;
import me.pgthinker.system.controller.vo.DocumentVO;
import me.pgthinker.system.service.ai.DocumentEntityService;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: me.pgthinker.system.controller
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 23:46
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
public class DocumentController {

	private final DocumentEntityService documentEntityService;

	@GetMapping("/list")
	public BaseResponse<Page<DocumentVO>> listDocument(DocumentVO documentVO) {
		return ResultUtils.success(documentEntityService.listDocuments(documentVO));
	}

	@PostMapping("/delete")
	public BaseResponse<Boolean> deleteKnowledgeFile(@RequestBody DocumentVO documentVO) {
		return ResultUtils.success(documentEntityService.deleteKnowledgeFile(documentVO));
	}

}
