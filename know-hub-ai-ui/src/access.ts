export type ThemeType = 'dark' | 'light';
export interface GlobalType {
  authVO?: API.AuthVO;
  theme?: ThemeType;
}

export default (initialState: GlobalType) => {
  // 在这里按照初始化数据定义项目中的权限，统一管理
  // 参考文档 https://umijs.org/docs/max/access
  const canSeeAdmin =
    initialState && initialState.authVO?.roles?.includes('admin');
  return {
    canSeeAdmin,
  };
};
