import { login } from '@/services/authController';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { history, useRequest } from '@umijs/max';
import { Button, Form, Input, message } from 'antd';

const LoginPage = () => {
  const [form] = Form.useForm();

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
          message.success('登录成功');
          localStorage.setItem('token', data.token);
          history.push('/');
        }
      },
      onError: (error) => {
        message.error('登录失败，请检查网络连接');
        console.log(error);
      },
    },
  );

  return (
    <div
      style={{
        maxWidth: 400,
        margin: '100px auto',
        padding: 40,
        boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
        borderRadius: 8,
      }}
    >
      <h1 style={{ textAlign: 'center', marginBottom: 30 }}>用户登录</h1>
      <Form form={form} onFinish={doLogin}>
        <Form.Item
          name="username"
          rules={[
            { required: true, message: '请输入用户名' },
            { min: 4, message: '用户名至少4个字符' },
          ]}
        >
          <Input prefix={<UserOutlined />} placeholder="用户名" />
        </Form.Item>

        <Form.Item
          name="password"
          rules={[
            { required: true, message: '请输入密码' },
            { min: 6, message: '密码至少6个字符' },
          ]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder="密码" />
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit" loading={loading} block>
            登录
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default LoginPage;
