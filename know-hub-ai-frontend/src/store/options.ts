// 配置信息全局状态管理
import { ChatOptions } from "@/api/dto";
import { defineStore } from "pinia";
export const CHAT_OPTIONS = "know-hub-ai-chat-config";
export const defaultChatOptions: ChatOptions = {
  model: "gpt-3.5-turbo",
  maxHistoryLength: 10,
  chatType: "simple",
  temperature: 0.5,
};
export const useChatOptionsStore = defineStore("user", {
  state: () => {
    // 尝试从本地获取配置信息
    let optionsToken: string | null = localStorage.getItem(CHAT_OPTIONS);

    let globalOptions: ChatOptions =
      optionsToken === null ? defaultChatOptions : JSON.parse(optionsToken);
    return {
      globalOptions,
    };
  },
  getters: {
    getChatOptions(): ChatOptions {
      return this.globalOptions;
    },
  },
  actions: {
    setChatOptions(globalOptions: ChatOptions) {
      // 本地存储
      localStorage.setItem(CHAT_OPTIONS, JSON.stringify(globalOptions));
      this.$patch({ globalOptions });
    },
  },
});
