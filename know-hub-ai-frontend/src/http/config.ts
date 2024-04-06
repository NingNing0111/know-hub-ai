const BACKEND_HOST =
  import.meta.env.VITE_BACKEND_HOST || "http://localhost:8306";

console.log(BACKEND_HOST);

// 后端接口地址
export const BASE_URL = BACKEND_HOST + "/api/v1";
// 请求头的基本信息
export const HEADER = {
  "Content-Type": "application/json;charset=UTF-8",
};
