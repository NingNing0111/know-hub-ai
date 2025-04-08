import { PlusSquareTwoTone } from '@ant-design/icons';
import { Button, GetProp, Menu, MenuProps, Select } from 'antd';
import './index.css';
type MenuItem = GetProp<MenuProps, 'items'>[number];
const ChatConversation = () => {
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
  return (
    <div className="base-box">
      <Button type="primary" icon={<PlusSquareTwoTone />}>
        开启新对话
      </Button>
      <div className="menu-box">
        <Menu items={items} className="menu-style"></Menu>
      </div>
      <div className="bottom-box">
        <Select
          style={{ width: '100%' }}
          defaultValue=""
          onChange={() => {}}
          options={[
            { value: '', label: '未选择知识库' },
            { value: 'jack', label: 'Jack' },
            { value: 'lucy', label: 'Lucy' },
            { value: 'Yiminghe', label: 'yiminghe' },
            { value: 'disabled', label: 'Disabled' },
          ]}
        />
      </div>
    </div>
  );
};

export default ChatConversation;
