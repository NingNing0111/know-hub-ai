import {
  listChatConversation,
  removeChatConversation,
} from '@/services/chatConversationController';
import { simpleKnowledgeBase } from '@/services/knowledgeBaseController';
import { DeleteOutlined, PlusSquareTwoTone } from '@ant-design/icons';
import { useModel } from '@umijs/max';
import {
  Button,
  Empty,
  Form,
  GetProp,
  Menu,
  MenuProps,
  message,
  Popconfirm,
  Select,
  Tooltip,
} from 'antd';
import { useEffect, useState } from 'react';
import './index.css';
type MenuItem = GetProp<MenuProps, 'items'>[number];

const chatOptions = [
  {
    value: 'simple',
    label: '简单对话',
  },
  {
    value: 'simpleRAG',
    label: '简单RAG对话',
  },
  {
    value: 'multimodal',
    label: '多模态对话',
  },
  {
    value: 'multimodalRAG',
    label: '多模态RAG对话',
  },
];
const ChatConversation = () => {
  const {
    curConversationId,
    setCurConverstationId,
    chatSetting,
    setChatSetting,
  } = useModel('chat');
  const [messageApi, contextHolder] = message.useMessage();

  const [baseOptions, setBaseOptions] = useState<
    { value: string; label: string }[]
  >([]);

  const [conversationItem, setConversationItem] = useState<MenuItem[]>([]);

  // 加载知识库列表
  const loadSimpleBaseList = async () => {
    try {
      const res = await simpleKnowledgeBase();
      if (res.code === 0 && res.data) {
        // 知识库选项
        const options = res.data.map((item) => ({
          value: item.id ?? '',
          label: item.name ?? '',
        }));
        setBaseOptions(options);
      } else {
        messageApi.error(res.message);
      }
    } catch (e) {
      console.log(e);
    }
  };
  // 加载对话列表
  const loadConversationList = async () => {
    try {
      const res = await listChatConversation();
      if (res.code === 0 && res.data) {
        const items = res.data.map((item) => {
          const menuItem: MenuItem = {
            key: item.id ?? '',
            label: item.title ?? '',
            extra: (
              <Tooltip title="删除对话记录">
                <Popconfirm
                  title="删除记录记录?"
                  description={`这会删除"${item.title}"。`}
                  onConfirm={async () => {
                    await removeConversation(item);
                  }}
                >
                  <DeleteOutlined style={{ color: 'red' }} />
                </Popconfirm>
              </Tooltip>
            ),
            onClick: () => {
              setCurConverstationId(item.id ?? '');
            },
          };
          return menuItem;
        });
        setConversationItem(items);
      } else {
        messageApi.error(res.message);
      }
    } catch (e) {
      console.log(e);
    }
  };
  // 删除对话
  const removeConversation = async (value: API.ChatConversationVO) => {
    try {
      const res = await removeChatConversation({ ...value });
      if (res.code === 0 && res.data) {
        messageApi.success('删除成功');
        setCurConverstationId('');
      } else {
        messageApi.error(res.message);
      }
    } finally {
      await loadConversationList();
    }
  };

  useEffect(() => {
    loadSimpleBaseList();
    loadConversationList();
  }, []);

  useEffect(() => {
    loadConversationList();
  }, [curConversationId]);
  return (
    <>
      {contextHolder}
      <div className="base-box">
        <div
          style={{ height: '20px', display: 'flex', justifyContent: 'center' }}
        >
          <Button
            type="primary"
            style={{
              width: '100%',
            }}
            icon={<PlusSquareTwoTone />}
            onClick={() => {
              setCurConverstationId('');
            }}
          >
            开启新对话
          </Button>
        </div>

        <div className="menu-box">
          {conversationItem.length > 0 ? (
            <Menu
              items={conversationItem}
              selectedKeys={
                curConversationId && curConversationId !== ''
                  ? [curConversationId]
                  : []
              }
              onSelect={(e) => {
                setCurConverstationId(e.key);
              }}
              className="menu-style"
            ></Menu>
          ) : (
            <Empty description="暂无对话" />
          )}
        </div>
        <div className="bottom-box">
          <Form
            layout="vertical"
            initialValues={chatSetting}
            onValuesChange={(value) => {
              setChatSetting({ ...chatSetting, ...value });
            }}
          >
            <Form.Item label="对话模式" name="chatType">
              <Select style={{ width: '100%' }} options={chatOptions} />
            </Form.Item>
            <Form.Item label="知识库" name="knowledgeIds">
              <Select
                style={{ width: '100%' }}
                options={baseOptions}
                placeholder="请选择知识库"
                mode="multiple"
                disabled={!chatSetting.chatType?.includes('RAG')}
              />
            </Form.Item>
          </Form>
        </div>
      </div>
    </>
  );
};

export default ChatConversation;
