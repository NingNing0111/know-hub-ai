import { login } from '@/services/authController';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import {
  LoginFormPage,
  ProConfigProvider,
  ProFormCheckbox,
  ProFormText,
} from '@ant-design/pro-components';
import { history, useRequest } from '@umijs/max';
import { message, Tabs, theme } from 'antd';
import { useState } from 'react';

type LoginType = 'email' | 'account';

const LoginPage = () => {
  const [messageApi, contextHolder] = message.useMessage();
  const [loginType, setLoginType] = useState<LoginType>('account');
  const { token } = theme.useToken();
  const { loading, run: doLogin } = useRequest(
    async (values) => {
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
      <div
        style={{
          backgroundColor: 'white',
          height: '100vh',
        }}
      >
        <LoginFormPage<API.UserLoginVO>
          loading={loading}
          onFinish={handleLoginFinish}
          logo={process.env.UMI_APP_LOGO}
          backgroundVideoUrl="https://gw.alipayobjects.com/v/huamei_gcee1x/afts/video/jXRBRK_VAwoAAAAAAAAAAAAAK4eUAQBr"
          title={process.env.UMI_APP_NAME}
          containerStyle={{
            backgroundColor: 'rgba(0, 0, 0,0.65)',
            backdropFilter: 'blur(4px)',
          }}
          subTitle="一个Umi.js + SpringBoot的开发模板"
        >
          <Tabs
            centered
            activeKey={loginType}
            onChange={(activeKey) => setLoginType(activeKey as LoginType)}
            items={[
              {
                label: '账号密码登录',
                key: 'account',
                children: (
                  <>
                    <ProFormText
                      name="username"
                      fieldProps={{
                        size: 'large',
                        autoComplete: 'username',
                        prefix: (
                          <UserOutlined
                            style={{
                              color: token.colorText,
                            }}
                            className={'prefixIcon'}
                          />
                        ),
                      }}
                      placeholder={'请输入用户名'}
                      rules={[
                        {
                          required: true,
                          message: '请输入用户名!',
                        },
                      ]}
                    />
                    <ProFormText.Password
                      name="password"
                      fieldProps={{
                        size: 'large',
                        autoComplete: 'current-password',
                        prefix: (
                          <LockOutlined
                            style={{
                              color: token.colorText,
                            }}
                            className={'prefixIcon'}
                          />
                        ),
                      }}
                      placeholder={'请输入密码'}
                      rules={[
                        {
                          required: true,
                          message: '请输入密码！',
                        },
                      ]}
                    />
                  </>
                ),
              },
              // {
              //   label: '邮箱登录',
              //   key: 'email',
              //   children: (
              //     <>
              //       <ProFormText
              //         fieldProps={{
              //           size: 'large',
              //           prefix: (
              //             <MailOutlined
              //               style={{
              //                 color: token.colorText,
              //               }}
              //               className={'prefixIcon'}
              //             />
              //           ),
              //         }}
              //         name="mail"
              //         placeholder={'请输入邮箱'}
              //         rules={[
              //           {
              //             required: true,
              //             message: '请输入邮箱！',
              //           },
              //           {
              //             pattern:
              //               /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
              //             message: '邮箱格式错误！',
              //           },
              //         ]}
              //       />
              //       <ProFormCaptcha
              //         fieldProps={{
              //           size: 'large',
              //           prefix: (
              //             <LockOutlined
              //               style={{
              //                 color: token.colorText,
              //               }}
              //               className={'prefixIcon'}
              //             />
              //           ),
              //         }}
              //         captchaProps={{
              //           size: 'large',
              //         }}
              //         placeholder={'请输入验证码'}
              //         captchaTextRender={(timing, count) => {
              //           if (timing) {
              //             return `${count} ${'获取验证码'}`;
              //           }
              //           return '获取验证码';
              //         }}
              //         name="captcha"
              //         rules={[
              //           {
              //             required: true,
              //             message: '请输入验证码！',
              //           },
              //         ]}
              //         onGetCaptcha={async () => {
              //           message.success('获取验证码成功！验证码为：1234');
              //         }}
              //       />
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
    <ProConfigProvider dark>
      <LoginPage />
    </ProConfigProvider>
  );
};
