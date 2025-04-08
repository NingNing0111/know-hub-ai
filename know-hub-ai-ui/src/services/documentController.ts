// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

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
