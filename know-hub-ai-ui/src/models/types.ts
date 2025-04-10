export type ChatType = 'simple' | 'simpleRAG' | 'multimodal' | 'multimodalRAG';

export type ChatSetting = {
  chatType: ChatType;
  knowledgeIds: string[];
};
