import { PlusSquareTwoTone } from '@ant-design/icons';
import { Button, Form, GetProp, Menu, MenuProps, Select } from 'antd';
import './index.css';
type MenuItem = GetProp<MenuProps, 'items'>[number];

interface Props {
  baseOptions: { value: string; label: string }[];
  chatOptions: { value: string; label: string }[];
  onChange: (value: any) => void;
  menuItems: MenuItem[];
}
const ChatConversation = (props: Props) => {
  const [form] = Form.useForm();
  return (
    <div className="base-box">
      <Button type="primary" icon={<PlusSquareTwoTone />}>
        开启新对话
      </Button>
      <div className="menu-box">
        <Menu items={props.menuItems} className="menu-style"></Menu>
      </div>
      <div className="bottom-box">
        <Form
          layout="vertical"
          form={form}
          onChange={(value) => {
            console.log(value);

            props.onChange(value);
          }}
        >
          <Form.Item label="对话模式" name="chatType">
            <Select
              style={{ width: '100%' }}
              defaultValue=""
              options={props.chatOptions}
            />
          </Form.Item>
          <Form.Item label="知识库" name="knowledgeId">
            <Select
              style={{ width: '100%' }}
              defaultValue=""
              options={props.baseOptions}
            />
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};

export default ChatConversation;
