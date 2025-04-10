import { PlusSquareTwoTone } from '@ant-design/icons';
import { useModel } from '@umijs/max';
import { Button, Empty, Form, GetProp, Menu, MenuProps, Select } from 'antd';
import './index.css';
type MenuItem = GetProp<MenuProps, 'items'>[number];

interface Props {
  baseOptions: { value: string; label: string }[];
  chatOptions: { value: string; label: string }[];
  menuItems: MenuItem[];
  onCreate: (value?: any) => void;
}
const ChatConversation = (props: Props) => {
  const {
    curConversationId,
    setCurConverstationId,
    chatSetting,
    setChatSetting,
  } = useModel('chat');

  return (
    <div className="base-box">
      <Button
        type="primary"
        style={{ height: '100px' }}
        icon={<PlusSquareTwoTone />}
        onClick={props.onCreate}
      >
        开启新对话
      </Button>
      <div className="menu-box">
        {props.menuItems.length > 0 ? (
          <Menu
            items={props.menuItems}
            selectedKeys={
              curConversationId && curConversationId !== ''
                ? [curConversationId]
                : []
            }
            onSelect={(e) => {
              setCurConverstationId(e.key);
            }}
            className="menu-style"
          ></Menu>
        ) : (
          <Empty description="暂无对话" />
        )}
      </div>
      <div className="bottom-box">
        <Form
          layout="vertical"
          initialValues={chatSetting}
          onValuesChange={(value) => {
            setChatSetting({ ...chatSetting, ...value });
          }}
        >
          <Form.Item label="对话模式" name="chatType">
            <Select style={{ width: '100%' }} options={props.chatOptions} />
          </Form.Item>
          <Form.Item label="知识库" name="knowledgeIds">
            <Select
              style={{ width: '100%' }}
              options={props.baseOptions}
              placeholder="请选择知识库"
              mode="multiple"
              disabled={!chatSetting.chatType?.includes('RAG')}
            />
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};

export default ChatConversation;
