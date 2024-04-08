import service from "@/http";
import { DrawApi } from "./common";
import { DrawImageDto } from "./dto";

export const drawApi = (drawDto: DrawImageDto): Promise<any> => {
  return service.post(DrawApi.DrawApi, drawDto);
};
