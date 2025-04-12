import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { ProFormText } from '@ant-design/pro-components';
import { theme } from 'antd';
const UsernamePassword = () => {
  const { token } = theme.useToken();
  return (
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
  );
};

export default UsernamePassword;
