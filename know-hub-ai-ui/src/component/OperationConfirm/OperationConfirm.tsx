import { Button, Popconfirm } from 'antd';

interface Props {
  title?: string;
  description?: string;
  okText?: string;
  cancelText?: string;
  btnName: string;
  danger?: boolean;
  onCancel?: (e: any) => void;
  onConfirm?: (e: any) => void;
  size?: 'middle' | 'small' | 'large';
  type?: 'default' | 'primary' | 'dashed' | 'link' | 'text';
  variant?: 'outlined' | 'dashed' | 'solid' | 'filled' | 'text' | 'link';
}
const OperationConfirm: React.FC<Props> = (props) => {
  return (
    <>
      <Popconfirm
        title={props.title}
        description={props.description}
        okText={props.okText}
        cancelText={props.cancelText}
        onCancel={props.onCancel}
        onConfirm={props.onConfirm}
      >
        <Button
          danger={props.danger}
          size={props.size}
          type={props.type}
          variant={props.variant}
        >
          {props.btnName}
        </Button>
      </Popconfirm>
    </>
  );
};

export default OperationConfirm;
