package me.pgthinker.system.controller;

import lombok.RequiredArgsConstructor;
import me.pgthinker.core.common.BaseResponse;
import me.pgthinker.core.common.ResultUtils;
import me.pgthinker.system.controller.vo.KnowledgeBaseVO;
import me.pgthinker.system.controller.vo.SimpleBaseVO;
import me.pgthinker.system.service.ai.KnowledgeBaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project: me.pgthinker.system.controller
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 07:59
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/knowledge")
public class KnowledgeBaseController {

	private final KnowledgeBaseService knowledgeBaseService;

	@PostMapping("/create")
	public BaseResponse<String> createKnowledgeBase(@RequestBody KnowledgeBaseVO knowledgeBase) {
		return ResultUtils.success(knowledgeBaseService.addKnowledgeBase(knowledgeBase));
	}

	@PostMapping("/remove")
	public BaseResponse<Integer> removeKnowledgeBase(@RequestBody KnowledgeBaseVO knowledgeBase) {
		return ResultUtils.success(knowledgeBaseService.removeKnowledgeBase(knowledgeBase));
	}

	@GetMapping("/list")
	public BaseResponse<List<KnowledgeBaseVO>> listKnowledgeBase() {
		return ResultUtils.success(knowledgeBaseService.knowLedgelist());
	}

	@GetMapping("/simple")
	public BaseResponse<List<SimpleBaseVO>> simpleKnowledgeBase() {
		return ResultUtils.success(knowledgeBaseService.simpleList());
	}

}
