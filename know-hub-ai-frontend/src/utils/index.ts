import useClipboard from "vue-clipboard3";

export const copy = async (text: string) => {
  const { toClipboard } = useClipboard();
  try {
    await toClipboard(text); //实现复制
  } catch (e) {
    console.error(e);
  }
};
