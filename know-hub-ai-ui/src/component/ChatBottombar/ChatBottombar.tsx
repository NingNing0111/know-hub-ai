import { PlusOutlined, SendOutlined } from '@ant-design/icons';
import { Button, Input, Tooltip } from 'antd';
import { useState } from 'react';
import './index.css';

const { TextArea } = Input;
interface Props {
  onSendMessage: (message: string) => Promise<void>;
}
const ChatBottombar = (props: Props) => {
  const [inputContent, setInputContent] = useState('');
  const [isSending, setIsSending] = useState(false);
  return (
    <>
      <div className="input-box">
        <div>
          <TextArea
            value={inputContent}
            autoSize={{ minRows: 1, maxRows: 1 }}
            style={{
              fontSize: '16px',
              border: 'none',
              background: 'none',
              outline: 'none',
              boxShadow: 'none',
            }}
            placeholder="询问任何问题"
            onChange={(e) => {
              setInputContent(e.target.value);
            }}
          ></TextArea>
        </div>
        <div className="input-tool">
          <Tooltip title="上传附件进行对话">
            <Button shape="circle" icon={<PlusOutlined size={20} />} />
          </Tooltip>
          <Button
            type="primary"
            icon={<SendOutlined />}
            disabled={isSending}
            onClick={async () => {
              try {
                setIsSending(true);
                await props.onSendMessage(inputContent);
                setInputContent('');
              } finally {
                setIsSending(false);
              }
            }}
          >
            发送
          </Button>
        </div>
      </div>
    </>
  );
};

export default ChatBottombar;
