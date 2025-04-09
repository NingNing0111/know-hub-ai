import { PlusOutlined, SendOutlined } from '@ant-design/icons';
import { Button, Input, Tooltip } from 'antd';
import ChatMessage from '../ChatMessage';
import './index.css';

const { TextArea } = Input;

const ChatWindow = () => {
  const messages = [
    {
      role: 'user',
      content: '2123123',
    },
    {
      role: 'assistant',
      content: `
# heading

* list
* items

\`\`\`js
function () {}
\`\`\`

$123$

$$
a^2 + b^2 = c^2
$$
`,
    },
    {
      role: 'user',
      content: '123123123',
    },
    {
      role: 'assistant',
      content: `
# heading

* list
* items

\`\`\`js
function () {}
\`\`\`

$123$

$$
a^2 + b^2 = c^2
$$
`,
    },
    {
      role: 'user',
      content: '123123123',
    },
    {
      role: 'assistant',
      content: `
# heading

* list
* items

\`\`\`js
function () {}
\`\`\`

$123$

$$
a^2 + b^2 = c^2
$$
`,
    },
  ];

  return (
    <div className="chat-window-box">
      <div className="chat-window-container">
        {messages.map((item) => {
          return <ChatMessage role={item.role} content={item.content} />;
        })}
      </div>
      <div className="input-box">
        <div>
          <TextArea
            autoSize={{ minRows: 1, maxRows: 1 }}
            style={{
              fontSize: '16px',
              border: 'none',
              background: 'none',
              outline: 'none',
              boxShadow: 'none',
            }}
            placeholder="询问任何问题"
          ></TextArea>
        </div>
        <div className="input-tool">
          <Tooltip title="上传附件进行对话">
            <Button shape="circle" icon={<PlusOutlined size={20} />} />
          </Tooltip>
          <Button type="primary" icon={<SendOutlined />}>
            发送
          </Button>
        </div>
      </div>
    </div>
  );
};

export default ChatWindow;
