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
	public Flux<ChatResponse> simpleChat(@RequestBody ChatMessageVO chatMessageVO) {
		return chatService.simpleChat(chatMessageVO);
	}

	@PostMapping("/chat/multimodal")
	public Flux<ChatResponse> multimodalChat(@RequestBody ChatMessageVO chatMessageVO) {
		return chatService.multimodalChat(chatMessageVO);
	}

	@PostMapping("/chat/simple/rag/{knowledgeBaseId}")
	public Flux<ChatResponse> simpleRAGChat(@PathVariable String knowledgeBaseId, @RequestBody ChatMessageVO chatMessageVO) {
		return chatService.simpleRAGChat(chatMessageVO, knowledgeBaseId);
	}

	@PostMapping("/chat/multimodal/rag/{knowledgeBaseId}")
	public Flux<ChatResponse> multimodalRAGChat(@PathVariable String knowledgeBaseId, @RequestBody ChatMessageVO chatMessageVO) {
		return chatService.multimodalRAGChat(chatMessageVO, knowledgeBaseId);
	}


}
