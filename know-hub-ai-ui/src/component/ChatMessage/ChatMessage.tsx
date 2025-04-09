import { CopyOutlined, RedoOutlined } from '@ant-design/icons';
import { Tooltip } from 'antd';
import { useState } from 'react';
import MarkdownContent from '../MarkdownContent';
import './index.css';
interface Props {
  role: string;
  content: string;
}
const ChatMessage = (props: Props) => {
  const [isHovered, setIsHovered] = useState(false);
  return (
    <div
      className="message-box"
      style={{
        alignItems: props.role === 'user' ? 'end' : 'start',
      }}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      <div className="content-container">
        <MarkdownContent content={props.content} />
      </div>
      {props.role === 'assistant' && (
        <div className="assistant-tool">
          {isHovered && (
            <Tooltip title="复制" placement="bottom">
              <CopyOutlined style={{ cursor: 'pointer' }} />
            </Tooltip>
          )}
          {isHovered && (
            <Tooltip title="重试" placement="bottom">
              <RedoOutlined size={20} style={{ cursor: 'pointer' }} />
            </Tooltip>
          )}
        </div>
      )}
    </div>
  );
};

export default ChatMessage;
