import { ReactComponent as RobotIcon } from '@/assets/icons/robat.svg';
import { ReactComponent as UserIcon } from '@/assets/icons/user.svg';
import { CopyOutlined, RedoOutlined } from '@ant-design/icons';
import { Avatar, Tooltip } from 'antd';
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
        alignItems: props.role.toLowerCase() === 'user' ? 'end' : 'start',
      }}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      <div
        className="content-container"
        style={{
          alignItems: props.role.toLowerCase() === 'user' ? 'end' : 'start',
        }}
      >
        <Avatar
          style={{
            background: 'none',
            marginBottom: '10',
          }}
          icon={
            props.role.toLowerCase() === 'user' ? <UserIcon /> : <RobotIcon />
          }
        />
        <MarkdownContent content={props.content} />
      </div>
      {props.role.toLowerCase() === 'assistant' && (
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
