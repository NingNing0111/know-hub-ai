import { ChatApi } from "./common";
import { ChatDTO } from "@/api/dto.ts";
import { BASE_URL } from "@/http/config";

import { useChatOptionsStore } from "@/store/options";
import { useChatMessageStore } from "@/store/message";
import { fetchEventSource } from "@microsoft/fetch-event-source";
import service from "@/http";
class FatalError extends Error {}
class RetriableError extends Error {}
const chatOptions = useChatOptionsStore();
const chatMessageStore = useChatMessageStore();

// 非流式对话
export const simpleChatApi = async (input: string): Promise<any> => {
  const dto: ChatDTO = {
    messages: chatMessageStore.getGlobalMessage,
    chatOptions: chatOptions.getChatOptions, // 对话配置从全局状态配置中获取
    prompt: input,
  };
  return await service.post(ChatApi.SimpleChat, dto);
};

// 获取所有对话模型
export const getModelsApi = async (): Promise<any> => {
  return await service.get(ChatApi.Models);
};

// 流式对话
export const streamChatApi = (input: string) => {
  // 添加用户提问
  chatMessageStore.addMessage({
    role: "user",
    content: input,
  });
  const dto: ChatDTO = {
    messages: chatMessageStore.getGlobalMessage,
    chatOptions: chatOptions.getChatOptions,
    prompt: input,
  };
  const ctrl = new AbortController();
  // 添加当前AI回复
  let answer = "";
  chatMessageStore.addMessage({
    role: "assistant",
    content: answer,
  });
  const onMessage = (e: any) => {
    let result = JSON.parse(e.data).results.at(0).output.content;
    if (result != null) {
      answer += result;
      chatMessageStore.setCurrMessage(answer);
    }
  };
  const onError = (err: any) => {
    // console.log("发生错误", err);
    throw err;
  };
  const onClose = () => {
    // 连接关闭 对话结束
    console.log("连接关闭");
    throw new RetriableError();
  };
  const onOpen = async (response: any) => {
    console.log("请求参数", dto);

    console.log(response);
    if (response.ok) {
      return;
    } else if (
      response.status >= 400 &&
      response.status < 500 &&
      response.status !== 429
    ) {
      throw new FatalError();
    } else {
      throw new RetriableError();
    }
  };
  fetchEventSource(BASE_URL + ChatApi.StreamChat, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(dto),
    signal: ctrl.signal,
    onmessage: onMessage,
    onerror: onError,
    onclose: onClose,
    onopen: onOpen,
  });
};
