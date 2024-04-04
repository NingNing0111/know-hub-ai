// 配置信息全局状态管理
import { Message } from "@/api/dto";
import { defineStore } from "pinia";
export const MESSAGE_LOCAL_STORE = "know-hub-ai-local-store";
export const defaultMessage: Message[] = [
  {
    role: "system",
    content: "你是一个人工智能助手",
  },
];
export const useChatMessageStore = defineStore("message", {
  state: () => {
    // 当前AI回复的信息
    let currAIMessage: string = "";

    // 尝试从本地获取配置信息
    let messagesJson: string | null = localStorage.getItem(MESSAGE_LOCAL_STORE);

    let globalMessage: Message[] =
      messagesJson === null ? defaultMessage : JSON.parse(messagesJson);
    return {
      globalMessage,
      currAIMessage,
    };
  },
  getters: {
    getGlobalMessage(): Message[] {
      return this.globalMessage;
    },
  },
  actions: {
    addMessage(message: Message) {
      this.globalMessage.push(message);
      localStorage.setItem(
        MESSAGE_LOCAL_STORE,
        JSON.stringify(this.globalMessage)
      );
    },
    cleanMessage() {
      this.globalMessage = defaultMessage;
      localStorage.removeItem(MESSAGE_LOCAL_STORE);
    },
    // 设置当前AI回复
    setCurrMessage(currAIMessage: string) {
      // 当前AI回复放在最后一个对话列表中 this.globalMessage 的类型时Message[]
      let len = this.globalMessage.length;
      this.globalMessage[len - 1].content = currAIMessage;
    },
  },
});
