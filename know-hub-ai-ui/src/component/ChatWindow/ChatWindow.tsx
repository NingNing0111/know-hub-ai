import {
  createChatConversation,
  detailChatConversation,
} from '@/services/chatConversationController';
import { uploadChatFile } from '@/services/originFileResourceController';
import { useModel } from '@umijs/max';
import { message } from 'antd';
import { useEffect, useRef, useState } from 'react';
import {
  CustomEventDataType,
  CustomEventReadyStateChangeType,
  CustomEventType,
  SSE,
  SSEOptionsMethod,
} from 'sse-ts';
import ChatBottombar from '../ChatBottombar/ChatBottombar';
import ChatList from '../ChatList';
import './index.css';

const ChatWindow = () => {
  const [messageApi, contextHolder] = message.useMessage();
  const { chatSetting, curConversationId, setCurConverstationId } =
    useModel('chat');
  const [curChatMessage, setCurChatMessage] = useState<API.ChatMessageVO[]>([]);
  const curChatMessageRef = useRef<API.ChatMessageVO[]>([]);
  const chatWindowRef = useRef<HTMLDivElement>(null);
  const curConversationIdRef = useRef<string>(curConversationId);
  const aiTextRef = useRef('');
  // 对话附件列表
  const chatFilesIdsRef = useRef<string[]>([]);
  // 加载对话内容
  const loadChatMessage = async () => {
    curChatMessageRef.current = [];
    setCurChatMessage([]);
    try {
      if (curConversationId === '') {
        return;
      }
      const res = await detailChatConversation({
        id: curConversationId,
      });
      if (res.code === 0 && res.data && res.data.messages) {
        curChatMessageRef.current = [...res.data.messages];
        setCurChatMessage([...curChatMessageRef.current]);
      }
    } catch (e) {
      console.log(e);
    }
  };
  useEffect(() => {
    if (chatWindowRef.current) {
      chatWindowRef.current.scrollTo({
        top: chatWindowRef.current.scrollHeight,
        behavior: 'smooth',
      });
    }
  }, [curChatMessage]);
  // 监听id状态 执行查询操作
  useEffect(() => {
    loadChatMessage();
    curConversationIdRef.current = curConversationId;
  }, [curConversationId]);

  const handleSendMsg = async (input: string) => {
    if (!curConversationId || curConversationId === '') {
      try {
        const res = await createChatConversation({
          title: input,
        });
        if (res.code === 0 && res.data && res.data.id) {
          curConversationIdRef.current = res.data.id;
        } else {
          messageApi.error('创建对话失败' + res.message);
        }
      } catch (e) {
        messageApi.error('创建对话失败');
      }
    }
    setCurConverstationId(curConversationIdRef.current);
    setTimeout(async () => {
      const newUserMessage: API.ChatMessageVO = {
        id: Math.random().toString(36),
        role: 'USER',
        content: input,
      };
      curChatMessageRef.current.push(newUserMessage);
      setCurChatMessage([...curChatMessageRef.current]);
      await handleChat(input);
    }, 500);
  };

  const handleChat = async (inputText: string) => {
    aiTextRef.current = '';
    const aiMessage: API.ChatMessageVO = {
      id: Math.random().toString(36),
      role: 'ASSISTANT',
      content: aiTextRef.current,
    };
    curChatMessageRef.current.push(aiMessage);
    setCurChatMessage([...curChatMessageRef.current]);

    const chatRequest: API.ChatRequestVO = {
      conversationId: curConversationIdRef.current ?? '',
      content: inputText,
      knowledgeIds: chatSetting.knowledgeIds,
      chatType: chatSetting.chatType,
      resourceIds: chatFilesIdsRef.current,
    };

    const source = new SSE(`/api/ai/chat/unify`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
      method: SSEOptionsMethod.POST,
      payload: JSON.stringify({
        ...chatRequest,
      }),
    });
    // 事件监听

    source.addEventListener('message', (e: CustomEventType) => {
      const dataEvent = e as CustomEventDataType;
      try {
        const data = JSON.parse(dataEvent.data);
        if (data.output && data.output.text !== null) {
          const payload = JSON.parse(dataEvent.data);
          const text = payload.output.text;
          aiTextRef.current = aiTextRef.current + text;
          if (
            curChatMessageRef.current &&
            curChatMessageRef.current.length > 0
          ) {
            const updatedMessages = [...curChatMessageRef.current];
            // 修改content值
            updatedMessages[updatedMessages.length - 1] = {
              ...updatedMessages[updatedMessages.length - 1],
              content: aiTextRef.current,
            };
            // 设置
            curChatMessageRef.current = updatedMessages;
          }
          setCurChatMessage([...curChatMessageRef.current]);
        }
      } catch (e) {
        messageApi.error('对话异常');
        console.log(e);
        loadChatMessage();
      }
    });

    source.addEventListener('readystatechange', (e: CustomEventType) => {
      const dataEvent = e as CustomEventReadyStateChangeType;
      if (dataEvent.readyState >= 2) {
        setCurChatMessage([...curChatMessageRef.current]);
        chatFilesIdsRef.current = [];
        source.close();
      }
    });

    source.stream();
  };

  const handleUploadChatFile = async (file: File) => {
    // 上传文件
    try {
      const res = await uploadChatFile({}, file);
      if (res.code === 0 && res.data) {
        chatFilesIdsRef.current.push(res.data ?? '');
        messageApi.success('文件上传成功');
      } else {
        messageApi.error(res.message);
      }
    } catch (e) {
      console.log(e);
      messageApi.error('文件上传失败');
    }
  };
  return (
    <>
      {contextHolder}
      <div className="chat-window-box">
        <ChatList chatWindowRef={chatWindowRef} messages={curChatMessage} />
        <ChatBottombar
          onSendMessage={handleSendMsg}
          uploadFile={handleUploadChatFile}
        />
      </div>
    </>
  );
};

export default ChatWindow;
