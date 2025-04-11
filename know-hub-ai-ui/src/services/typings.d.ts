declare namespace API {
  type AssistantMessage = {
    messageType?: 'USER' | 'ASSISTANT' | 'SYSTEM' | 'TOOL';
    metadata?: Record<string, any>;
    toolCalls?: ToolCall[];
    media?: Media[];
    text?: string;
  };

  type AuthVO = {
    username?: string;
    token?: string;
    roles?: string[];
  };

  type BaseResponseAuthVO = {
    code?: number;
    data?: AuthVO;
    message?: string;
  };

  type BaseResponseBoolean = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseChatConversationVO = {
    code?: number;
    data?: ChatConversationVO;
    message?: string;
  };

  type BaseResponseInteger = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponseListChatConversationVO = {
    code?: number;
    data?: ChatConversationVO[];
    message?: string;
  };

  type BaseResponseListKnowledgeBaseVO = {
    code?: number;
    data?: KnowledgeBaseVO[];
    message?: string;
  };

  type BaseResponseListSimpleBaseVO = {
    code?: number;
    data?: SimpleBaseVO[];
    message?: string;
  };

  type BaseResponseLong = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageDocumentVO = {
    code?: number;
    data?: PageDocumentVO;
    message?: string;
  };

  type BaseResponseString = {
    code?: number;
    data?: string;
    message?: string;
  };

  type ChatConversationVO = {
    id?: string;
    title?: string;
    createTime?: string;
    messages?: ChatMessageVO[];
  };

  type ChatGenerationMetadata = {
    empty?: boolean;
    contentFilters?: string[];
    finishReason?: string;
  };

  type ChatMessageVO = {
    id?: string;
    conversationId?: string;
    messageNo?: number;
    content?: string;
    role?: string;
    resourceIds?: string[];
    resources?: ResourceVO[];
  };

  type ChatRequestVO = {
    conversationId: string;
    content: string;
    resourceIds?: string[];
    knowledgeIds?: string[];
    chatType: string;
  };

  type detailChatConversationParams = {
    id: string;
  };

  type DocumentVO = {
    pageNo?: number;
    pageSize?: number;
    knowledgeBaseId?: string;
    id?: number;
    fileName?: string;
    path?: string;
    isEmbedding?: boolean;
    baseId?: string;
    knowledgeBaseName?: string;
    fileType?: string;
    uploadTime?: string;
  };

  type Generation = {
    metadata?: ChatGenerationMetadata;
    output?: AssistantMessage;
  };

  type KnowledgeBaseVO = {
    id?: string;
    name: string;
    description?: string;
    author?: number;
    authorName?: string;
    createTime?: string;
  };

  type listDocumentParams = {
    arg0: DocumentVO;
  };

  type Media = {
    id?: string;
    mimeType?: MimeType;
    data?: Record<string, any>;
    name?: string;
    dataAsByteArray?: string;
  };

  type MimeType = {
    type?: string;
    subtype?: string;
    parameters?: Record<string, any>;
    wildcardType?: boolean;
    wildcardSubtype?: boolean;
    subtypeSuffix?: string;
    charset?: string;
    concrete?: boolean;
  };

  type OrderItem = {
    column?: string;
    asc?: boolean;
  };

  type PageDocumentVO = {
    records?: DocumentVO[];
    total?: number;
    size?: number;
    current?: number;
    orders?: OrderItem[];
    optimizeCountSql?: PageDocumentVO;
    searchCount?: PageDocumentVO;
    optimizeJoinOfCountSql?: boolean;
    maxLimit?: number;
    countId?: string;
    pages?: number;
  };

  type ResourceVO = {
    resourceId?: string;
    fileName?: string;
    fileType?: string;
    path?: string;
  };

  type SimpleBaseVO = {
    id?: string;
    name?: string;
  };

  type ToolCall = {
    id?: string;
    type?: string;
    name?: string;
    arguments?: string;
  };

  type uploadKnowledgeFileParams = {
    knowledgeId: string;
  };

  type UserLoginVO = {
    username: string;
    password: string;
  };
}
