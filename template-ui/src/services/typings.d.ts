declare namespace API {
  type AuthVO = {
    username?: string;
    token?: string;
    roles?: string[];
  };

  type BaseResponseAuthVO = {
    code?: number;
    data?: AuthVO;
    message?: string;
  };

  type UserLoginVO = {
    username: string;
    password: string;
  };
}
