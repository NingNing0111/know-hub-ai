import ChatConversation from '@/component/ChatConversation';
import ChatWindow from '@/component/ChatWindow';
import { PageContainer } from '@ant-design/pro-components';
import { useModel } from '@umijs/max';
import { GetProp, MenuProps } from 'antd';
import './index.css';
type MenuItem = GetProp<MenuProps, 'items'>[number];
const ChatPage = () => {
  const { menuCollapsed, setMenuCollapsed } = useModel('collapsed');
  const items: MenuItem[] = [
    {
      key: '1',
      label: 'Navigation One',
    },
    {
      key: '2',
      label: 'Navigation Two',
    },
  ];
  const baseOptions = [
    {
      value: '1',
      label: '测试知识库1',
    },
    {
      value: '2',
      label: '测试知识库2',
    },
  ];
  const chatOptions = [
    {
      value: '1',
      label: '简单对话',
    },
    {
      value: '2',
      label: '简单RAG对话',
    },
    {
      value: '3',
      label: '多模态对话',
    },
    {
      value: '4',
      label: '多模态RAG对话',
    },
  ];

  const changeSetting = (value: any) => {
    console.log(value);
  };
  return (
    <>
      <PageContainer title={false}>
        <div className="chat-page-box">
          {/* 左边导航栏 */}
          {menuCollapsed && (
            <ChatConversation
              menuItems={items}
              baseOptions={baseOptions}
              chatOptions={chatOptions}
              onChange={changeSetting}
            />
          )}
          {/* 右边对话界面 */}
          <ChatWindow />
        </div>
      </PageContainer>
    </>
  );
};

export default ChatPage;
