package com.ningning0111.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author ：何汉叁
 * @date ：2024/4/7 20:32
 * @description：TODO
 */
@SpringBootTest
public class MinioTest {
    @Test
    public void testUploadFile() throws Exception {
        InputStream is = new FileInputStream("C:/Users/33135/Desktop/file.txt"); // 替换为你的测试文件路径
        MultipartFile multipartFile = new MockMultipartFile("file.txt", "file.txt", "text/plain", is);
        String url = MinioUtil.uploadFile(multipartFile);
        System.out.println(url);
        assertNotNull(url);
    }

//    @Test
//    public void testDeleteFile() throws Exception {
//        MinioUtil.deleteFile("202404072110401712495440645file.txt");
//    }
}
