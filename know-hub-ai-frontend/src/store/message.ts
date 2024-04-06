// 配置信息全局状态管理
import { Message } from "@/api/dto";
import { defineStore } from "pinia";
import { useChatOptionsStore } from "./options";
const optionsStore = useChatOptionsStore();
export const MESSAGE_LOCAL_STORE = "know-hub-ai-local-store";
const defaultMessage: Message[] = [
  {
    role: "system",
    content: optionsStore.getSystemPrompt,
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

    // 设置当前AI回复
    setCurrMessage(currAIMessage: string) {
      // 当前AI回复放在最后一个对话列表中 this.globalMessage 的类型时Message[]
      let len = this.globalMessage.length;
      this.globalMessage[len - 1].content = currAIMessage;
    },
    setGlobalMessage(globalMessage: Message[]) {
      this.$patch({ globalMessage });
    },
    resetGlobalMessage() {
      localStorage.removeItem(MESSAGE_LOCAL_STORE);
      this.globalMessage = [];
      this.addMessage(defaultMessage.at(0) as Message);
      console.log(this.globalMessage);
    },
    storeMessage() {
      localStorage.setItem(
        MESSAGE_LOCAL_STORE,
        JSON.stringify(this.getGlobalMessage)
      );
    },
  },
});
