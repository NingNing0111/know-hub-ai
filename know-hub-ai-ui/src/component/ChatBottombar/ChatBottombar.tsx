import { fileToBlob } from '@/utils/file';
import { PlusOutlined, SendOutlined } from '@ant-design/icons';
import { ProFormUploadButton } from '@ant-design/pro-components';
import { Button, Image, Input, Tooltip } from 'antd';
import { UploadFile } from 'antd/lib';
import { useState } from 'react';
import './index.css';

const { TextArea } = Input;
interface Props {
  onSendMessage: (message: string) => Promise<void>;
  uploadFile: (file: File) => Promise<void>;
}
const ChatBottombar = (props: Props) => {
  const [inputContent, setInputContent] = useState('');
  const [isSending, setIsSending] = useState(false);
  const [uploading, setUploading] = useState(false);
  const [fileList, setFileList] = useState<UploadFile[]>([]);
  const [fileBlobList, setBlobList] = useState<
    { blob: Blob; fileName: string }[]
  >([]);

  return (
    <>
      {fileBlobList.length > 0 && (
        <div className="image-preview-list">
          {fileBlobList.map((file) =>
            file.blob.type.startsWith('image/') ? (
              <div className="image-preview-item">
                <Image
                  width={100}
                  src={URL.createObjectURL(file.blob)}
                  alt="Preview"
                />
              </div>
            ) : (
              <span>{file.fileName}</span>
            ),
          )}
        </div>
      )}
      <div className="input-box">
        <div>
          <TextArea
            value={inputContent}
            autoSize={{ minRows: 1, maxRows: 1 }}
            style={{
              fontSize: '16px',
              border: 'none',
              background: 'none',
              outline: 'none',
              boxShadow: 'none',
            }}
            placeholder="询问任何问题"
            onChange={(e) => {
              setInputContent(e.target.value);
            }}
          ></TextArea>
        </div>
        <div className="input-tool">
          <ProFormUploadButton
            disabled={uploading}
            title={false}
            label={false}
            fieldProps={{
              maxCount: 10,
              multiple: true,
              fileList: fileList,
              beforeUpload: async (file) => {
                try {
                  setUploading(true);
                  const blob = await fileToBlob(file);
                  setFileList([...fileList, file]);
                  setBlobList([...fileBlobList, { blob, fileName: file.name }]);
                  await props.uploadFile(file);
                  return false;
                } finally {
                  setUploading(false);
                }
              },
              showUploadList: false,
            }}
            icon={
              <Tooltip title="上传附件进行对话">
                <PlusOutlined size={20} />
              </Tooltip>
            }
            buttonProps={{ shape: 'circle' }}
          />
          <Button
            type="primary"
            icon={<SendOutlined />}
            disabled={isSending}
            onClick={async () => {
              try {
                setIsSending(true);
                await props.onSendMessage(inputContent);
                setInputContent('');
              } finally {
                setIsSending(false);
                setFileList([]);
                setBlobList([]);
              }
            }}
          >
            发送
          </Button>
        </div>
      </div>
    </>
  );
};

export default ChatBottombar;
