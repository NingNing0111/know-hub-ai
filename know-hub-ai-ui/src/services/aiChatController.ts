// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 此处后端没有提供注释 POST /ai/chat/unify */
export async function unifyChat(
  body: API.ChatRequestVO,
  options?: { [key: string]: any },
) {
  return request<API.Generation[]>(`/api/ai/chat/unify`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
