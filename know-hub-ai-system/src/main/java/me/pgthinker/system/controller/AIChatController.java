package me.pgthinker.system.controller;

import lombok.RequiredArgsConstructor;
import me.pgthinker.system.controller.vo.ChatMessageVO;
import me.pgthinker.system.service.ai.AIChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @Project: me.pgthinker.system.controller
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 05:21
 * @Description:
 */
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIChatController {

	private final AIChatService chatService;

	@PostMapping("/chat/simple")
	public Flux<ChatResponse> chat(@RequestBody ChatMessageVO chatMessageVO) {
		return chatService.simpleChat(chatMessageVO);
	}

	@PostMapping("/chat/{knowledgeBaseId}")
	public Flux<ChatResponse> chat(@PathVariable String knowledgeBaseId, @RequestBody ChatMessageVO chatMessageVO) {
		return chatService.ragChat(chatMessageVO, knowledgeBaseId);
	}

}
