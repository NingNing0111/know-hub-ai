import axios from "axios";
import { BASE_URL, HEADER } from "./config";

const service = axios.create({
  baseURL: BASE_URL,
  //   timeout: 10000,
  withCredentials: false,
  headers: HEADER,
});

// 创建请求拦截器
service.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token !== null) {
      config.headers.Authorization = "Bearer " + token;
    }
    return config;
  },
  (error) => {
    console.log(error);

    return Promise.reject(error);
  }
);

// 创建响应拦截器
service.interceptors.response.use(
  (res: any) => {
    return res.data;
  },
  (error) => {
    let message = "";
    console.log(error);
    return Promise.reject(message);
  }
);

export default service;
