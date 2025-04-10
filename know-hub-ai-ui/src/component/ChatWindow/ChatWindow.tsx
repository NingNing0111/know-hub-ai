import { LegacyRef } from 'react';
import ChatBottombar from '../ChatBottombar/ChatBottombar';
import ChatList from '../ChatList';
import './index.css';

interface Props {
  messages?: API.ChatMessageVO[];
  onSendMessage: (message: string) => Promise<void>;
  chatWindowRef: LegacyRef<any>;
  uploadFile: (file: File) => Promise<void>;
}
const ChatWindow = (props: Props) => {
  return (
    <div className="chat-window-box">
      <ChatList chatWindowRef={props.chatWindowRef} messages={props.messages} />
      <ChatBottombar
        onSendMessage={props.onSendMessage}
        uploadFile={props.uploadFile}
      />
    </div>
  );
};

export default ChatWindow;
