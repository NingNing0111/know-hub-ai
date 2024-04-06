package com.ningning0111.controller;

import com.ningning0111.common.*;
import com.ningning0111.exception.BusinessException;
import com.ningning0111.model.dto.ChatDTO;
import com.ningning0111.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;


/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 16:38
 * @Description:
 */
@Tag(name="ChatController",description = "对话接口")
@RestController
@RequiredArgsConstructor
@RequestMapping(ApplicationConstant.API_VERSION + "/chat")
@Slf4j
public class ChatController {
    private final ChatService chatService;
    @Operation(summary = "stream",description = "流式对话接口")
    @PostMapping(value = "/stream")
    public Flux<ChatResponse> streamRagChat(
            @RequestBody ChatDTO chatRequest
    ){
        String chatTypeStr = chatRequest.chatOptions().chatType();
        ChatType chatType = ChatType.getChatType(chatTypeStr);
        switch (chatType){
            case RAG -> {
                return chatService.ragChat(chatRequest);
            }
            case SIMPLE -> {
                return chatService.simpleChat(chatRequest);
            }
            default -> throw new BusinessException(ErrorCode.PARAMS_ERROR,"暂不支持["+chatTypeStr+"]对话");
        }
    }

    @Operation(summary = "simple",description = "简单对话接口")
    @PostMapping(value = "/simple")
    public BaseResponse simpleChat(
            @RequestBody ChatDTO chatRequest
    ){
        String chatTypeStr = chatRequest.chatOptions().chatType();
        ChatType chatType = ChatType.getChatType(chatTypeStr);
        switch (chatType){
            case RAG -> {
                return chatService.noStreamRagChat(chatRequest);
            }
            case SIMPLE -> {
                return chatService.noStreamSimpleChat(chatRequest);
            }
            default -> throw new BusinessException(ErrorCode.PARAMS_ERROR,"暂不支持["+chatTypeStr+"]对话");
        }
    }
    @Operation(summary = "models",description = "获取模型列表")
    @GetMapping(value = "/models")
    public BaseResponse models(){
        List<String> models = LLMModels.getModels();
        return ResultUtils.success(models);
    }




}
