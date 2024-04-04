package com.ningning0111.controller;

import com.ningning0111.common.*;
import com.ningning0111.exception.BusinessException;
import com.ningning0111.model.dto.ChatRequest;
import com.ningning0111.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.http.MediaType;
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
@RestController
@RequiredArgsConstructor
@RequestMapping(ApplicationConstant.API_VERSION + "/chat")
@Slf4j
public class ChatController {
    private final ChatService chatService;

    @PostMapping(value = "/stream")
    public Flux<ChatResponse> streamRagChat(
            @RequestBody ChatRequest chatRequest
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


    @PostMapping(value = "/simple")
    public BaseResponse simpleChat(
            @RequestBody ChatRequest chatRequest
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

    @GetMapping(value = "/models")
    public BaseResponse models(){
        List<String> models = LLMModels.getModels();
        return ResultUtils.success(models);
    }




}
