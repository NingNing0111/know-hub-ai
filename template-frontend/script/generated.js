import { generate } from "openapi-typescript-codegen";
import * as dotenv from "dotenv";

dotenv.config();

if (!process.env.OPENAPI_SPEC_URL) {
  throw new Error("OPENAPI_SPEC_URL environment variable is not set");
}

const generateOpenAPI = () => {
  generate({
    input: process.env.OPENAPI_SPEC_URL,
    output: "src/api",
    httpClient: "axios",
    // 更多配置选项
    useOptions: true,
    useUnionTypes: true,
    exportCore: true,
    exportSchemas: true,
    exportModels: true,
    exportServices: true,
  }).then(() => {
    console.log("API client generated successfully");
  });
};

generateOpenAPI();
