import { OpenAIOutlined } from '@ant-design/icons';
import { LegacyRef } from 'react';
import ChatMessage from '../ChatMessage';
import './index.css';
interface Props {
  messages: API.ChatMessageVO[];
  chatWindowRef: LegacyRef<any>;
}
const ChatList = (props: Props) => {
  return (
    <>
      <div className="chat-window-container" ref={props.chatWindowRef}>
        {props.messages.length > 0 ? (
          props.messages.map((item) => {
            return (
              <ChatMessage
                key={item.id}
                role={item.role ?? ''}
                content={item.content ?? ''}
                resource={item.resources?.map((item) => {
                  return {
                    type: item.fileType ?? '',
                    url: item.path ?? '',
                    fileName: item.fileName ?? '',
                  };
                })}
              />
            );
          })
        ) : (
          <div className="chat-window-empty-box">
            <OpenAIOutlined />
            <span>How can I help you?</span>
          </div>
        )}
      </div>
    </>
  );
};

export default ChatList;
