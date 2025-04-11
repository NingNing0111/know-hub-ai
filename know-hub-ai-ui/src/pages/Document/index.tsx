import { listDocument } from '@/services/documentController';
import { uploadKnowledgeFile } from '@/services/originFileResourceController';
import { downloadFile } from '@/utils/file';
import type { ActionType, ProColumns } from '@ant-design/pro-components';
import {
  PageContainer,
  ProFormUploadDragger,
  ProTable,
} from '@ant-design/pro-components';
import { useNavigate, useParams } from '@umijs/max';
import { Button, Divider, message, Tag } from 'antd';
import { useEffect, useRef, useState } from 'react';

const DocumentPage = () => {
  const param = useParams();
  const navigator = useNavigate();
  const [messageApi, contextHolder] = message.useMessage();
  const [loading, setLoading] = useState<boolean>(false);
  const [documentList, setDocumentList] = useState<API.DocumentVO[]>([]);
  // const downloadFile = async (fileId: ) => {
  //   try {
  //     const res = await downloadDocument({fileId: fileId});

  //   }catch (error) {
  //     messageApi.error('下载失败');
  //   }
  // }
  const columns: ProColumns<API.DocumentVO>[] = [
    {
      title: '文件名',
      dataIndex: 'fileName',
      valueType: 'text',
      width: 150,
    },
    {
      title: '文件类型',
      dataIndex: 'fileType',
      valueType: 'text',
      width: 150,
      search: false,
      render: (dom, entity, index, action, schema) => {
        return <Tag color="green">{entity.fileType}</Tag>;
      },
    },
    {
      title: '上传时间',
      dataIndex: 'uploadTime',
      valueType: 'dateTime',
      width: 150,
      search: false,
    },
    {
      title: '操作',
      valueType: 'option',
      width: 150,
      render: (_, record) => {
        return (
          <Button
            type="primary"
            size="small"
            onClick={async () => {
              if (record.path && record.path !== '') {
                downloadFile(record.path);
              } else {
                messageApi.error('文件不存在');
              }
            }}
          >
            查看
          </Button>
        );
      },
    },
  ];
  const actionRef = useRef<ActionType>();
  const queryParam = useRef<API.DocumentVO>({
    pageNo: 1,
    pageSize: 10,
    fileName: '',
  } as API.DocumentVO);
  const [total, setTotal] = useState<number>(0);

  const loadDocumentList = async () => {
    setLoading(true);
    try {
      const res = await listDocument({
        arg0: {
          ...queryParam.current,
          knowledgeBaseId: param.knowledgeBaseId,
        },
      });
      if (res.code !== 0) {
        messageApi.error(res.message);
      } else {
        setTotal(res.data?.total ?? 0);
        setDocumentList(res.data?.records ?? []);
      }
    } finally {
      setLoading(false);
    }
  };

  const uploadFile = async (file: File) => {
    setLoading(true);
    try {
      const res = await uploadKnowledgeFile(
        { knowledgeId: param.knowledgeBaseId as any },
        {},
        file,
      );
      if (res.code === 0) {
        messageApi.success('上传成功');
        loadDocumentList();
      } else {
        messageApi.error(res.message);
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadDocumentList();
  }, []);
  return (
    <>
      {contextHolder}
      <PageContainer
        ghost
        title="知识库文档"
        extra={
          <Button onClick={() => navigator(-1)} type="primary">
            返回{' '}
          </Button>
        }
      >
        <ProFormUploadDragger
          max={1}
          fieldProps={{
            beforeUpload: async (file) => {
              await uploadFile(file);
              return false;
            },
          }}
        />
        <Divider />
        <ProTable<API.DocumentVO>
          loading={loading}
          columns={columns}
          actionRef={actionRef}
          dataSource={documentList}
          rowKey="id"
          search={{
            labelWidth: 'auto',
          }}
          pagination={{
            pageSize: queryParam.current.pageSize,
            total: total,
            current: queryParam.current.pageNo,
            onChange: (page) => {
              queryParam.current.pageNo = page;
              loadDocumentList();
            },
          }}
          toolBarRender={false}
          onSubmit={async (param) => {
            queryParam.current = { ...queryParam.current, ...param };
            await loadDocumentList();
          }}
          dateFormatter="string"
        />
      </PageContainer>
    </>
  );
};

export default DocumentPage;
