package com.ningning0111.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author ：何汉叁
 * @date ：2024/4/13 19:34
 * @description：截取url的文件名
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MatchUtil {
    public String getMinioFileName(String url) {
        int endIndex = url.contains("?") ? url.indexOf("?") : url.length();
        return url.substring(url.lastIndexOf("/") + 1,endIndex);
    }
}
