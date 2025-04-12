import { login } from '@/services/authController';
import {
  LoginFormPage,
  ProConfigProvider,
  ProFormCheckbox,
} from '@ant-design/pro-components';
import { history, useRequest, useSearchParams } from '@umijs/max';
import { message, Tabs, theme } from 'antd';
import { CSSProperties, useState } from 'react';
import UsernamePassword from './components/UsernamePassword';
type LoginType = 'email' | 'account';

const LoginPage = () => {
  const { token } = theme.useToken();
  const [searchParams] = useSearchParams();

  const iconStyles: CSSProperties = {
    marginInlineStart: '16px',
    color: token.colorTextBase,
    fontSize: '24px',
    verticalAlign: 'middle',
    cursor: 'pointer',
  };
  const [messageApi, contextHolder] = message.useMessage();
  const [loginType, setLoginType] = useState<LoginType>('account');
  const { loading, run: doLogin } = useRequest(
    async (values: API.UserLoginVO) => {
      return await login({
        ...values,
      });
    },
    {
      manual: true,
      onSuccess: (data) => {
        if (data && data.token) {
          localStorage.setItem('token', data.token);
          messageApi.success('登录成功');
          setTimeout(() => {
            history.push('/');
          }, 1000);
        }
      },
      onError: (error) => {
        messageApi.error('登录失败，请检查网络连接');
        console.log(error);
      },
    },
  );
  const handleLoginFinish = async (
    values: API.UserLoginVO,
  ): Promise<boolean> => {
    const res = await doLogin(values);
    return !!res?.token;
  };

  return (
    <>
      {contextHolder}
      <div style={{ backgroundColor: token.colorBgContainer, height: '100vh' }}>
        <LoginFormPage<API.UserLoginVO>
          loading={loading}
          onFinish={handleLoginFinish}
          logo={process.env.UMI_APP_LOGO}
          backgroundVideoUrl="https://gw.alipayobjects.com/v/huamei_gcee1x/afts/video/jXRBRK_VAwoAAAAAAAAAAAAAK4eUAQBr"
          title={process.env.UMI_APP_NAME}
          containerStyle={{
            backgroundColor: 'rgba(0, 0, 0,0.65)',
            backdropFilter: 'blur(4px)',
            color: token.colorText,
          }}
          subTitle={process.env.UMI_APP_SUB_TITLE}
          // actions={
          //   <Space style={{ marginTop: '10px' }}>
          //     其他登录方式:
          //     <GithubOutlined style={iconStyles}  />
          //   </Space>
          // }
        >
          <Tabs
            centered
            activeKey={loginType}
            onChange={(activeKey) => setLoginType(activeKey as LoginType)}
            items={[
              {
                label: '账号密码登录',
                key: 'account',
                children: <UsernamePassword />,
              },
              // {
              //   label: '邮箱登录',
              //   key: 'email',
              //   children: (
              //     <>
              //       <EmailCaptcha />
              //     </>
              //   ),
              // },
            ]}
          ></Tabs>

          <div
            style={{
              marginBlockEnd: 24,
            }}
          >
            <ProFormCheckbox noStyle name="autoLogin">
              自动登录
            </ProFormCheckbox>
            {/* <a
            style={{
              float: 'right',
            }}
          >
            忘记密码
          </a> */}
          </div>
        </LoginFormPage>
      </div>
    </>
  );
};

export default () => {
  return (
    <ProConfigProvider hashed={false}>
      <LoginPage />
    </ProConfigProvider>
  );
};
