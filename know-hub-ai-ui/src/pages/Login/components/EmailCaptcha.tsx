import { LockOutlined, MailOutlined } from '@ant-design/icons';
import { ProFormCaptcha, ProFormText } from '@ant-design/pro-components';
import { message, theme } from 'antd';
const EmailCaptcha = () => {
  const { token } = theme.useToken();
  return (
    <>
      <ProFormText
        fieldProps={{
          size: 'large',
          prefix: (
            <MailOutlined
              style={{
                color: token.colorText,
              }}
              className={'prefixIcon'}
            />
          ),
        }}
        name="mail"
        placeholder={'请输入邮箱'}
        rules={[
          {
            required: true,
            message: '请输入邮箱！',
          },
          {
            pattern: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
            message: '邮箱格式错误！',
          },
        ]}
      />
      <ProFormCaptcha
        fieldProps={{
          size: 'large',
          prefix: (
            <LockOutlined
              style={{
                color: token.colorText,
              }}
              className={'prefixIcon'}
            />
          ),
        }}
        captchaProps={{
          size: 'large',
        }}
        placeholder={'请输入验证码'}
        captchaTextRender={(timing: any, count: any) => {
          if (timing) {
            return `${count} ${'获取验证码'}`;
          }
          return '获取验证码';
        }}
        name="captcha"
        rules={[
          {
            required: true,
            message: '请输入验证码！',
          },
        ]}
        onGetCaptcha={async () => {
          message.success('获取验证码成功！验证码为：1234');
        }}
      />
    </>
  );
};

export default EmailCaptcha;
