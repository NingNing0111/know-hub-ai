/**
 * 后端API地址
 */
export const enum KnowApi {
  UploadFile = "/know/file/upload",
  QueryFile = "/know/contents",
  DeleteFile = "/know/delete",
}

export const enum ChatApi {
  StreamChat = "/chat/stream",
  SimpleChat = "/chat/simple",
  Models = "/chat/models",
}

export const enum OneApi {
  AddOneApi = "/one-api",
  QueryApi = "/select",
  ChangeApi = "/change/",
  QueryOneApi = "/select/",
  DeleteOneApi = "/delete/",
  DeleteApi = "/delete",
}

export const enum DrawApi {
  DrawApi = "/draw/",
}
