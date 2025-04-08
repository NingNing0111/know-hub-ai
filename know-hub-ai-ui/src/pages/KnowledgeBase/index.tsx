import KnowledgeForm from '@/component/KnowledgeForm/KnowledgeForm';
import OperationConfirm from '@/component/OperationConfirm';
import PageExtraButtons from '@/component/PageExtraButtons';
import {
  createKnowledgeBase,
  listKnowledgeBase,
  removeKnowledgeBase,
} from '@/services/knowledgeBaseController';
import { PlusCircleOutlined, RedoOutlined } from '@ant-design/icons';
import {
  PageContainer,
  ProDescriptions,
  ProList,
} from '@ant-design/pro-components';
import { useNavigate } from '@umijs/max';
import { Avatar, Button, Empty, message } from 'antd';
import { useEffect, useState } from 'react';
const KnowledgeBasePage = () => {
  const [messageApi, contextHolder] = message.useMessage();
  const [knowledgeList, setKnowledgeList] = useState<API.KnowledgeBaseVO[]>([]);
  const [loading, setLoading] = useState(false);
  const navigator = useNavigate();
  const loadKnowledgeList = async () => {
    setLoading(true);
    try {
      const res = await listKnowledgeBase();
      if (res.code == 0) {
        setKnowledgeList(res.data as any);
      } else {
        messageApi.error(res.message);
      }
    } finally {
      setLoading(false);
    }
  };
  const handleAddKnowledge = async (values: any) => {
    try {
      const res = await createKnowledgeBase({ ...values });
      if (res.code == 0) {
        messageApi.success('新增成功');
      } else {
        messageApi.error(res.message);
      }
    } finally {
      loadKnowledgeList();
    }
  };
  const handleDeleteKnowledge = async (values: any) => {
    try {
      const res = await removeKnowledgeBase({ ...values });
      if (res.code == 0) {
        messageApi.success('删除成功');
      } else {
        messageApi.error(res.message);
      }
    } finally {
      loadKnowledgeList();
    }
  };
  useEffect(() => {
    loadKnowledgeList();
  }, []);

  const extraButtons = [
    <Button
      type="default"
      icon={<RedoOutlined size={20} />}
      onClick={() => {
        loadKnowledgeList();
      }}
    >
      刷新
    </Button>,
    <KnowledgeForm
      title="新增知识库"
      btnName="新增"
      btnIcon={<PlusCircleOutlined size={20} />}
      type="add"
      onFinish={handleAddKnowledge}
    />,
  ];

  return (
    <>
      {contextHolder}
      <PageContainer
        loading={loading}
        ghost
        extra={<PageExtraButtons buttons={extraButtons} />}
      >
        {knowledgeList.length == 0 ? (
          <Empty />
        ) : (
          <ProList<API.KnowledgeBaseVO>
            pagination={false}
            showActions="hover"
            grid={{ gutter: 16, column: 3 }}
            bordered
            ghost
            metas={{
              avatar: {
                render: () => {
                  return <Avatar size={55} src="logo.png" />;
                },
              },
              content: {
                dataIndex: 'description',
                render(dom, entity, index, action, schema) {
                  return (
                    <>
                      <ProDescriptions
                        title={`知识库名称：${entity.name}`}
                        dataSource={entity}
                        column={1}
                      >
                        <ProDescriptions.Item label="创建人" valueType="text">
                          {entity.authorName}
                        </ProDescriptions.Item>
                        <ProDescriptions.Item
                          label="创建时间"
                          dataIndex="createTime"
                          valueType="dateTime"
                        />
                      </ProDescriptions>
                    </>
                  );
                },
              },

              actions: {
                render: (_, record) => {
                  return (
                    <div
                      style={{
                        display: 'flex',
                        gap: 10,
                      }}
                    >
                      <Button
                        type="primary"
                        size="small"
                        onClick={() => {
                          navigator(`/knowlegeBase/${record.id}`);
                        }}
                      >
                        详情
                      </Button>
                      <OperationConfirm
                        size="small"
                        btnName="删除"
                        danger
                        type="primary"
                        title="确定要删除这个知识库吗？"
                        onConfirm={async () => {
                          await handleDeleteKnowledge(record);
                        }}
                      />
                    </div>
                  );
                },
              },
            }}
            dataSource={knowledgeList}
          />
        )}
      </PageContainer>
    </>
  );
};

export default KnowledgeBasePage;
