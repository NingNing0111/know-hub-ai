// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 此处后端没有提供注释 POST /conversation/create */
export async function createChatConversation(
  body: API.ChatConversationVO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseChatConversationVO>(
    `/api/conversation/create`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      data: body,
      ...(options || {}),
    },
  );
}

/** 此处后端没有提供注释 GET /conversation/list */
export async function listChatConversation(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listChatConversationParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseChatConversationVO>(`/api/conversation/list`, {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
