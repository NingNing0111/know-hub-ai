import {AddDto, SelectDto} from "@/api/dto.ts";
import service from "@/http";

type Res = any

export const selectById = (id: number): Promise<Res> => {
    return service.get('/one-api/select/' + id)
}

export const selectApi = (page: number, pageSize: number): Promise<Res> => {
    const dto: SelectDto = {
        page: page,
        pageSize: pageSize
    }
    return service.post('/one-api/select', dto)
}

export const addOneApi = (): Promise<Res> => {
    const dto: AddDto = {
        baseUrl: "https://api.mnzdna.xyz",
        apiKey: "sk-W0KF2fh5BTX3fEUvBf1262B948354c03B079C94956CfD4B5",
        describe: "前端"
    }
    return service.post('/one-api/add', dto)
}

export const changeApi = (id: number): Promise<Res> => {
    return service.put('/one-api/change/' + id)
}

export const deleteById = (id: number): Promise<Res> => {
    return service.delete('/one-api/delete/' + id)
}
