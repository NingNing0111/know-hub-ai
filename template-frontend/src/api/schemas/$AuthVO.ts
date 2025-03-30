/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $AuthVO = {
    properties: {
        username: {
            type: 'string',
        },
        token: {
            type: 'string',
        },
        roles: {
            type: 'array',
            contains: {
                type: 'string',
            },
        },
    },
} as const;
