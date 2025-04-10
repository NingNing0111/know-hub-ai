package me.pgthinker.system.controller;

import lombok.RequiredArgsConstructor;
import me.pgthinker.core.common.BaseResponse;
import me.pgthinker.core.common.ResultUtils;
import me.pgthinker.system.controller.vo.ChatConversationVO;
import me.pgthinker.system.service.ai.ChatConversationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project: me.pgthinker.system.controller
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 05:07
 * @Description:
 */
@RestController
@RequestMapping("/conversation")
@RequiredArgsConstructor
public class ChatConversationController {

	private final ChatConversationService chatConversationService;

	@PostMapping("/create")
	public BaseResponse<ChatConversationVO> createChatConversation(@RequestBody ChatConversationVO chatConversationVO) {
		return ResultUtils.success(chatConversationService.createConversation(chatConversationVO));
	}

	@GetMapping("/detail")
	public BaseResponse<ChatConversationVO> detailChatConversation(@RequestParam(name = "id") String id) {
		return ResultUtils.success(chatConversationService.getConversation(id));
	}

	@GetMapping("/list")
	public BaseResponse<List<ChatConversationVO>> listChatConversation() {
		return ResultUtils.success(chatConversationService.listConversation());
	}

	@PostMapping("/remove")
	public BaseResponse<Boolean> removeChatConversation(@RequestBody ChatConversationVO chatConversationVO) {
		return ResultUtils.success(chatConversationService.removeConversation(chatConversationVO.getId()));
	}

}
