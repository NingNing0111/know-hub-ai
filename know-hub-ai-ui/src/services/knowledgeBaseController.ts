// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 此处后端没有提供注释 POST /knowledge/create */
export async function createKnowledgeBase(
  body: API.KnowledgeBaseVO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseString>(`/api/knowledge/create`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /knowledge/list */
export async function listKnowledgeBase(options?: { [key: string]: any }) {
  return request<API.BaseResponseListKnowledgeBaseVO>(`/api/knowledge/list`, {
    method: 'GET',
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 POST /knowledge/remove */
export async function removeKnowledgeBase(
  body: API.KnowledgeBaseVO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseInteger>(`/api/knowledge/remove`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /knowledge/simple */
export async function simpleKnowledgeBase(options?: { [key: string]: any }) {
  return request<API.BaseResponseListSimpleBaseVO>(`/api/knowledge/simple`, {
    method: 'GET',
    ...(options || {}),
  });
}
