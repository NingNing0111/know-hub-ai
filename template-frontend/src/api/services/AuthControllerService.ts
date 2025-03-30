/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponseAuthVO } from '../models/BaseResponseAuthVO';
import type { UserLoginVO } from '../models/UserLoginVO';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class AuthControllerService {
    /**
     * @returns BaseResponseAuthVO OK
     * @throws ApiError
     */
    public static login({
        requestBody,
    }: {
        requestBody: UserLoginVO,
    }): CancelablePromise<BaseResponseAuthVO> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/auth/login',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
}
