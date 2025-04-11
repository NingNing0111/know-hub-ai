// copy功能 参数为content
export const copyContent = (content: string) => {
  navigator.clipboard.writeText(content);
};
