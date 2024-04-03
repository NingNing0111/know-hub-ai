import {ChatApi} from "./common";
import {BASE_URL} from "@/http/config";
import axios from "axios";
import {ChatDTO} from "@/api/dto.ts";

const service = axios.create({
    baseURL: BASE_URL
});

export const simpleChatApi = async (input: string): Promise<any> => {
    const dto: ChatDTO = {
        messages: [],
        chatOptions: {
            model: "gpt-3.5-turbo",
            maxHistoryLength: 10,
            chatType: "rag",
            temperature: 0.8
        },
        prompt: input
    }
    return await service.post(ChatApi.SimpleChat, dto);
};
