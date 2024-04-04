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

export const addOneApi = (dto: AddDto): Promise<Res> => {
    return service.post('/one-api/add', dto)
}

export const changeApi = (id: number): Promise<Res> => {
    return service.put('/one-api/change/' + id)
}

export const deleteById = (id: number): Promise<Res> => {
    return service.delete('/one-api/delete/' + id)
}
