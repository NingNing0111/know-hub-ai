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
  };

  type ChatResponse = {
    result?: Generation;
    results?: Generation[];
    metadata?: ChatResponseMetadata;
  };

  type ChatResponseMetadata = {
    id?: string;
    model?: string;
    rateLimit?: RateLimit;
    usage?: Usage;
    promptMetadata?: PromptMetadata;
    empty?: boolean;
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
    output?: AssistantMessage;
    metadata?: ChatGenerationMetadata;
  };

  type KnowledgeBaseVO = {
    id?: string;
    name: string;
    description?: string;
    author?: number;
    authorName?: string;
    createTime?: string;
  };

  type listChatConversationParams = {
    id: string;
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
    charset?: string;
    concrete?: boolean;
    wildcardType?: boolean;
    wildcardSubtype?: boolean;
    subtypeSuffix?: string;
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

  type PromptMetadata = true;

  type RateLimit = {
    requestsLimit?: number;
    requestsRemaining?: number;
    requestsReset?: {
      seconds?: number;
      zero?: boolean;
      nano?: number;
      negative?: boolean;
      units?: {
        durationEstimated?: boolean;
        timeBased?: boolean;
        dateBased?: boolean;
      }[];
    };
    tokensLimit?: number;
    tokensRemaining?: number;
    tokensReset?: {
      seconds?: number;
      zero?: boolean;
      nano?: number;
      negative?: boolean;
      units?: {
        durationEstimated?: boolean;
        timeBased?: boolean;
        dateBased?: boolean;
      }[];
    };
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

  type Usage = {
    promptTokens?: number;
    completionTokens?: number;
    totalTokens?: number;
    nativeUsage?: Record<string, any>;
  };

  type UserLoginVO = {
    username: string;
    password: string;
  };
}
