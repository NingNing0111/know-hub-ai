import { ReactComponent as RobotIcon } from '@/assets/icons/robat.svg';
import { ReactComponent as UserIcon } from '@/assets/icons/user.svg';
import { copyContent } from '@/utils/keyboard';
import { CopyOutlined, LoadingOutlined, RedoOutlined } from '@ant-design/icons';
import { Avatar, Image, message, Tooltip } from 'antd';
import { useState } from 'react';
import MarkdownContent from '../MarkdownContent';
import './index.css';
interface Props {
  role: string;
  content: string;
  resource?: {
    fileName: string;
    type: string;
    url: string;
  }[];
}
const ChatMessage = (props: Props) => {
  const [isHovered, setIsHovered] = useState(false);
  const isUserContent = props.role.toLowerCase() === 'user';

  const isImage = (type: string) => type.startsWith('image/');

  return (
    <div
      className="message-box"
      style={{
        alignItems: isUserContent ? 'end' : 'start',
      }}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      <div
        className="content-container"
        style={{
          alignItems: isUserContent ? 'end' : 'start',
        }}
      >
        <Avatar
          style={{
            background: 'none',
            marginBottom: '10px', // Fixed the missing unit
          }}
          icon={isUserContent ? <UserIcon /> : <RobotIcon />}
        />
        {props.resource &&
          props.resource.map((item, index) => (
            <div key={index} style={{ marginBottom: '10px' }}>
              {isImage(item.type) ? (
                <Image src={item.url} alt={item.fileName} width={200} />
              ) : (
                <a href={item.url} target="_blank" rel="noopener noreferrer">
                  {item.fileName}
                </a>
              )}
            </div>
          ))}
        {!isUserContent &&
          (props.content === '' ? (
            <LoadingOutlined />
          ) : (
            <MarkdownContent content={props.content} />
          ))}
        {isUserContent && <MarkdownContent content={props.content} />}
      </div>
      {!isUserContent && (
        <div className="assistant-tool">
          {isHovered && (
            <Tooltip title="复制" placement="bottom">
              <CopyOutlined
                style={{ cursor: 'pointer' }}
                onClick={() => {
                  message.success('复制成功');
                  copyContent(props.content);
                }}
              />
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
