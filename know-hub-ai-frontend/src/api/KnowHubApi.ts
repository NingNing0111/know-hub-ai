import { KnowApi } from "./common";
import { BASE_URL } from "@/http/config";
import axios from "axios";
import { UploadFileDTO } from "./dto";

const service = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "multipart/form-data",
  },
});

export const uploadFileApi = async (filesList: File[]): Promise<any> => {
  let formData = new FormData();
  filesList.map((e) => {
    formData.append("file", e);
  });

  const res = await service.post(KnowApi.UploadFile, formData);
  return res;
};
