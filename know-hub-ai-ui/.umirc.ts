import { defineConfig } from '@umijs/max';

export default defineConfig({
  plugins: ['@umijs/max-plugin-openapi'],
  antd: {
    dark: true,
  },
  access: {},
  model: {},
  initialState: {},
  request: {
    dataField: 'data',
  },
  layout: {},
  routes: [
    {
      path: '/login',
      name: 'login',
      component: '@/pages/Login',
      title: '登录',
      layout: false,
    },
    {
      path: '/',
      redirect: '/home',
    },
    {
      path: '/home',
      name: '首页',
      title: '首页',
      component: '@/pages/Home',
      icon: 'HomeOutlined',
    },
    { path: '/*', component: '@/pages/404' },
  ],
  npmClient: 'pnpm',
  proxy: {
    '/api': {
      target: process.env.BASE_URL || 'http://localhost:8788/api',
      changeOrigin: true,
      secure: false,
      pathRewrite: {
        '^/api': '',
      },
    },
  },
  openAPI: [
    {
      requestLibPath: "import { request } from '@umijs/max'",
      schemaPath: `${process.env.BASE_URL}/v3/api-docs/default`, // openapi 接口地址
      mock: false,
      apiPrefix() {
        return "'/api'";
      },
    },
  ],
});
