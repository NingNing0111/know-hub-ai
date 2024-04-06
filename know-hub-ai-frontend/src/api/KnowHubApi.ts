import { KnowApi } from "./common";
import { BASE_URL } from "@/http/config";
import axios from "axios";
import { DeleteFileDto, QueryFileDto } from "./dto";
import service from "@/http";

type Res = any;

const fileService = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "multipart/form-data",
  },
});

// 上传知识库接口
export const uploadFileApi = async (filesList: File[]): Promise<Res> => {
  let formData = new FormData();
  filesList.map((e) => {
    formData.append("file", e);
  });

  return fileService.post(KnowApi.UploadFile, formData);
};

// 查询所有知识库接口
export const queryFileApi = async (params: QueryFileDto): Promise<Res> => {
  console.log("请求参数：", params);

  return service.get(KnowApi.QueryFile, {
    params,
  });
};

// 删除指定ID列表的知识库
export const deleteFileApi = async (params: DeleteFileDto): Promise<Res> => {
  return service.delete(KnowApi.DeleteFile, {
    params,
  });
};
