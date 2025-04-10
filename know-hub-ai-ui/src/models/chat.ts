import { useState } from 'react';
import { ChatSetting } from './types';

const useChat = () => {
  const [curConversationId, setCurConverstationId] = useState<string>('');
  const [chatSetting, setChatSetting] = useState<ChatSetting>({
    chatType: 'simple',
    knowledgeIds: [],
  });

  return {
    curConversationId,
    setCurConverstationId,
    chatSetting,
    setChatSetting,
  };
};

export default useChat;
