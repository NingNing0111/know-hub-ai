// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 此处后端没有提供注释 POST /ai/chat/multimodal */
export async function multimodalChat(
  body: API.ChatMessageVO,
  options?: { [key: string]: any },
) {
  return request<API.ChatResponse[]>(`/api/ai/chat/multimodal`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /ai/chat/multimodal/rag/${param0} */
export async function multimodalRagChat(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.multimodalRAGChatParams,
  body: API.ChatMessageVO,
  options?: { [key: string]: any },
) {
  const { knowledgeBaseId: param0, ...queryParams } = params;
  return request<API.ChatResponse[]>(`/api/ai/chat/multimodal/rag/${param0}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    params: { ...queryParams },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /ai/chat/simple */
export async function simpleChat(
  body: API.ChatMessageVO,
  options?: { [key: string]: any },
) {
  return request<API.ChatResponse[]>(`/api/ai/chat/simple`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /ai/chat/simple/rag/${param0} */
export async function simpleRagChat(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.simpleRAGChatParams,
  body: API.ChatMessageVO,
  options?: { [key: string]: any },
) {
  const { knowledgeBaseId: param0, ...queryParams } = params;
  return request<API.ChatResponse[]>(`/api/ai/chat/simple/rag/${param0}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    params: { ...queryParams },
    data: body,
    ...(options || {}),
  });
}
