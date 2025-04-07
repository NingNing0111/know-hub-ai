// 运行时配置

import {
  AxiosResponse,
  history,
  RequestConfig,
  RunTimeLayoutConfig,
} from '@umijs/max';
import { message } from 'antd';
import { userInfo } from './services/authController';

// 全局初始化数据配置，用于 Layout 用户信息和权限初始化
// 更多信息见文档：https://umijs.org/docs/api/runtime-config#getinitialstate
export async function getInitialState(): Promise<API.AuthVO> {
  const res = await userInfo();
  if (res && res.data) {
    return res.data;
  } else {
    history.push('/login');
  }
  return {};
}

export const layout: RunTimeLayoutConfig = ({ initialState }) => {
  return {
    title: process.env.UMI_APP_NAME,
    logo: 'https://img.alicdn.com/tfs/TB1YHEpwUT1gK0jSZFhXXaAtVXa-28-27.svg',
    menu: {
      locale: false,
      type: 'group',
    },
    onPageChange: () => {},
  };
};

// 请求配置

interface ResponseData<T = any> {
  code: number;
  data: T;
  message: string;
}

export const request: RequestConfig = {
  errorConfig: {
    errorThrower: (res) => {
      console.log('=====>', res);
    },
    errorHandler: (error: any) => {
      // if (error.response) {
      //   const status = error.response.status;
      //   if (status === 403) {
      //     history.push('/login');
      //   }
      // }
    },
  },
  requestInterceptors: [
    (url, options) => {
      // 拦截请求配置，进行个性化处理。
      const token = localStorage.getItem('token');
      if (token) {
        options.headers = {
          ...options.headers,
          Authorization: `Bearer ${token}`,
        };
      }

      return { url, options };
    },
  ],
  responseInterceptors: [
    (response: AxiosResponse) => {
      // 拦截响应数据，进行个性化处理
      const data: ResponseData = response.data;
      if (data.code !== 0) {
        message.error(data.message);
      }
      return response;
    },
  ],
};
