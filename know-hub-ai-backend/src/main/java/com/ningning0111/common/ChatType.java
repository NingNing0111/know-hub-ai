package com.ningning0111.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: com.ningning0111.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 19:53
 * @Description: 聊天类型，RAG模式和普通对话模式
 */
public enum ChatType {
    RAG("rag"),
    SIMPLE("simple");
    private final String describe;

    ChatType(String describe){
        this.describe = describe;
    }

    public String getDescribe(){
        return this.describe;
    }


    public static ChatType getChatType(String chatTypeStr){
        ChatType[] values = ChatType.values();
        for(ChatType chatType: values){
            if(chatType.getDescribe().equals(chatTypeStr)){
                return chatType;
            }
        }
        return null;
    }
}
