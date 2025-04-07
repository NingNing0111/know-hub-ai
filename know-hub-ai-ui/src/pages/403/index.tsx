import { history } from '@umijs/max';
import { Button, Result } from 'antd';

const NoAuthPage = () => {
  const goHome = () => {
    history.push('/home');
  };
  return (
    <Result
      status="403"
      title="403"
      subTitle="对不起，您无权限访问该页面"
      extra={
        <Button type="primary" onClick={goHome}>
          返回首页
        </Button>
      }
    />
  );
};

export default NoAuthPage;
