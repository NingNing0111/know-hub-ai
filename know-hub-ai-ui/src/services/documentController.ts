// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 此处后端没有提供注释 POST /document/delete */
export async function deleteKnowledgeFile(
  body: API.DocumentVO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean>(`/api/document/delete`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /document/download/${param0} */
export async function downloadDocument(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.downloadDocumentParams,
  options?: { [key: string]: any },
) {
  const { fileId: param0, ...queryParams } = params;
  return request<any>(`/api/document/download/${param0}`, {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /document/list */
export async function listDocument(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listDocumentParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageDocumentVO>(`/api/document/list`, {
    method: 'GET',
    params: {
      ...params,
      arg0: undefined,
      ...params['arg0'],
    },
    ...(options || {}),
  });
}
