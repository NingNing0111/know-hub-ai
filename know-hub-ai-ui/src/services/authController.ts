// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 此处后端没有提供注释 POST /auth/login */
export async function login(
  body: API.UserLoginVO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAuthVO>(`/api/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /auth/userInfo */
export async function userInfo(options?: { [key: string]: any }) {
  return request<API.BaseResponseAuthVO>(`/api/auth/userInfo`, {
    method: 'GET',
    ...(options || {}),
  });
}
