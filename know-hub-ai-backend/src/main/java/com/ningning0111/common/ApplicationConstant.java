package com.ningning0111.common;

/**
 * @Project: com.ningning0111.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 17:42
 * @Description:
 */
public class ApplicationConstant {
    public final static String API_VERSION = "/api/v1";
    public final static String APPLICATION_NAME = "know-hub-ai";

    public final static String DEFAULT_BASE_URL = "https://api.openai.com";

    public final static String SYSTEM_PROMPT = """
            你需要使用文档内容对用户提出的问题进行回复，同时你需要表现得天生就知道这些内容，
            不能在回复中体现出你是根据给出的文档内容进行回复的，这一点非常重要。
            
            当用户提出的问题让你无法根据文档内容进行回复或者你也不清楚时，回复不知道即可；
            所有的回答都应该优先参考文档内容，其次是你自身具备的知识；
                    
            文档内容如下:
            {documents}
            
            """;
}
