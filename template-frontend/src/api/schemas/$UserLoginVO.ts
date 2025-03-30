/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $UserLoginVO = {
    properties: {
        username: {
            type: 'string',
            isRequired: true,
            maxLength: 30,
            minLength: 5,
        },
        password: {
            type: 'string',
            isRequired: true,
            maxLength: 24,
            minLength: 6,
        },
    },
} as const;
