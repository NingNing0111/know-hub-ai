import { ChatApi } from "./common";
import { ChatDTO } from "@/api/dto.ts";
import request from "@/http"; // 使用封装的axios对象

import { useChatOptionsStore } from "@/store/options";

const chatOptions = useChatOptionsStore();

// 非流式对话
export const simpleChatApi = async (input: string): Promise<any> => {
  const dto: ChatDTO = {
    messages: [], // 历史聊天记录从本地获取
    chatOptions: chatOptions.getChatOptions, // 对话配置从全局状态配置中获取
    prompt: input,
  };
  return await request.post(ChatApi.SimpleChat, dto);
};

// 获取所有对话模型
export const getModelsApi = async (): Promise<any> => {
  return await request.get(ChatApi.Models);
};
