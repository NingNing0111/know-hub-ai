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
const SYSTEM_PROMPT = "你是一名AI助手，致力于帮助人们解决问题.";
export const useChatOptionsStore = defineStore("options", {
  state: () => {
    // 尝试从本地获取配置信息
    let optionsToken: string | null = localStorage.getItem(CHAT_OPTIONS);
    let systemPrompt: string =
      localStorage.getItem(SYSTEM_PROMPT) == null
        ? SYSTEM_PROMPT
        : (localStorage.getItem(SYSTEM_PROMPT) as string);
    let globalOptions: ChatOptions =
      optionsToken === null ? defaultChatOptions : JSON.parse(optionsToken);
    return {
      globalOptions,
      systemPrompt,
    };
  },
  getters: {
    getChatOptions(): ChatOptions {
      return this.globalOptions;
    },
    getSystemPrompt(): string {
      return this.systemPrompt;
    },
  },
  actions: {
    setChatOptions(globalOptions: ChatOptions) {
      // 本地存储
      localStorage.setItem(CHAT_OPTIONS, JSON.stringify(globalOptions));
      this.$patch({ globalOptions });
    },
    setSystemPrompt(systemPrompt: string) {
      localStorage.setItem(SYSTEM_PROMPT, systemPrompt);
      this.$patch({ systemPrompt });
    },
  },
});
