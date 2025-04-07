import { history } from '@umijs/max';
import { Button, Result } from 'antd';

const NoFoundPage = () => {
  const goHome = () => {
    history.push('/home');
  };
  return (
    <Result
      status="404"
      title="404"
      subTitle="Sorry, the page you visited does not exist."
      extra={
        <Button type="primary" onClick={goHome}>
          返回首页
        </Button>
      }
    />
  );
};

export default NoFoundPage;
