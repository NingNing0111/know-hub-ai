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
    public final static String DEFAULT_DESCRIBE = "分享自:[PG Thinker's Blog](https://pgthinker.me/2023/10/03/196/)";
    public final static String SYSTEM_PROMPT = """
        Use the information from the DOCUMENTS section to provide accurate answers but act as if you knew this information innately.
        If unsure, simply state that you don't know.
        Another thing you need to note is that your reply must be in Chinese!
        DOCUMENTS:
            {documents}    
        """;
}
