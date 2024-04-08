/**
 * 请求对象
 */
export interface Message {
  role: string;
  content: string;
}

export interface ChatOptions {
  model: string;
  maxHistoryLength: number;
  chatType: string;
  temperature: number;
}

export interface ChatDTO {
  messages: Message[];
  chatOptions: ChatOptions;
  prompt: string;
}

export interface UploadFileDTO {
  files: File[];
}

export interface SelectDto {
  page: number;
  pageSize: number;
}
export interface AddDto {
  baseUrl: string;
  apiKey: string;
  describe: string;
}

export interface QueryFileDto {
  page: number;
  pageSize: number;
  fileName: string | undefined;
}

export interface DeleteFileDto {
  ids: number[];
}

export interface DrawOptions {
  model: string;
  width: number;
  height: number;
  format: string;
}

export interface DrawImageDto {
  prompt: string;
  options: DrawOptions;
}
