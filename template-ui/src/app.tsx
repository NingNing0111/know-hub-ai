// src/app.ts
import { UserOutlined } from '@ant-design/icons';
import {
  AxiosResponse,
  history,
  RequestConfig,
  RunTimeLayoutConfig,
} from '@umijs/max';
import { message } from 'antd';
import { CiBellOn } from 'react-icons/ci';
import { GlobalType, ThemeType } from './access';
import ThemeSwitcher from './component/ThemeSwitcher';
import { userInfo } from './services/authController';

// 白名单路由，不拦截（如登录页）
const loginWhiteList = ['/login'];

export async function getInitialState(): Promise<GlobalType> {
  const token = localStorage.getItem('token');
  const theme: ThemeType =
    localStorage.getItem('vite-ui-theme') === 'dark' ? 'dark' : 'light';

  // 无 token，跳转登录
  if (!token) {
    const { location } = history;
    if (!loginWhiteList.includes(location.pathname)) {
      history.push('/login');
    }
    return { authVO: undefined, theme };
  }

  // 有 token，尝试获取用户信息
  try {
    const res = await userInfo();
    if (res?.data) {
      return { authVO: res.data, theme };
    }
  } catch (e) {
    console.error('获取用户信息失败:', e);
  }

  // 如果获取失败，跳转登录页
  history.push('/login');
  return { authVO: undefined, theme };
}

export const layout: RunTimeLayoutConfig = ({ initialState }) => {
  return {
    title: process.env.UMI_APP_NAME,
    logo: process.env.UMI_APP_LOGO,
    menu: {
      locale: false,
      type: 'group',
    },
    layout: 'mix',
    actionsRender: () => [
      <ThemeSwitcher key="theme-switch" />,
      <CiBellOn size={35} key="bell" />,
    ],
    avatarProps: {
      icon: <UserOutlined />,
      title: initialState?.authVO?.username
        ? `欢迎您, ${initialState.authVO.username}`
        : '',
    },
    // 页面切换时拦截未登录
    onPageChange: () => {
      const { location } = history;
      const token = localStorage.getItem('token');
      if (!token && !loginWhiteList.includes(location.pathname)) {
        history.push('/login');
      }
    },
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
    errorThrower: () => {},
    errorHandler: () => {},
  },
  requestInterceptors: [
    (url, options) => {
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
      const data: ResponseData = response.data;
      if (data.code !== 0) {
        message.error(data.message);
      }
      return response;
    },
  ],
};
