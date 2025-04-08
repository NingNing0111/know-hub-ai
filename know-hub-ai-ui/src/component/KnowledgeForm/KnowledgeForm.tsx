import {
  ModalForm,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-components';
import { Button } from 'antd';
import { ReactNode } from 'react';

interface Props {
  type: 'add' | 'update';
  title: string;
  btnIcon?: ReactNode;
  btnName: string;
  serverOptions?: { label: string; value: string }[];
  onFinish: (formData: any) => Promise<void>;
  trigger?: () => Promise<void>;
  initialValues?: any;
  btnSize?: 'small' | 'large' | 'middle';
}
const KnowledgeForm: React.FC<Props> = (props) => {
  return (
    <ModalForm
      layout="horizontal"
      autoFocusFirstInput
      title={props.title}
      trigger={
        <Button
          type="primary"
          icon={props.btnIcon}
          onClick={props.trigger}
          size={props.btnSize}
        >
          {props.btnName}
        </Button>
      }
      onFinish={async (formData) => {
        await props.onFinish(formData);
        return true;
      }}
    >
      <ProFormText
        name="name"
        width="md"
        label="名称"
        placeholder="请输入知识库名称"
        disabled={props.type === 'update'}
        initialValue={props.initialValues?.name}
      />

      <ProFormTextArea
        initialValue={props.initialValues?.description}
        label="描述"
        placeholder="请输入知识库描述"
        name="description"
      />
    </ModalForm>
  );
};

export default KnowledgeForm;
