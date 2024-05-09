package com.ningning0111.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.QueryFileDTO;
import com.ningning0111.service.impl.StoreFileServiceByMinioImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    StoreFileServiceByMinioImpl storeFileServiceByMinioImpl;

    @Autowired
    MinioUtil minioUtil;
    @Test
    public void testUploadFile() throws Exception {
        InputStream is = new FileInputStream("C:/Users/33135/Desktop/file.txt"); // 替换为你的测试文件路径
        MultipartFile multipartFile = new MockMultipartFile("file.txt", "file.txt", "text/plain", is);
        String url = minioUtil.uploadFile(multipartFile);
        System.out.println(url);
        assertNotNull(url);
    }

    /**
     * @author 何汉叁
     * @description Minio在service层的数据处理
     * @date 2024/4/13 19:10
     */
    @Test
    public void testUploadFileService() throws Exception {
        InputStream is = new FileInputStream("C:/Users/33135/Desktop/file.txt"); // 替换为你的测试文件路径
        MultipartFile multipartFile = new MockMultipartFile("file.txt", "file.txt", "text/plain", is);
        storeFileServiceByMinioImpl.fileStore(multipartFile);
        QueryFileDTO queryFileDTO = new QueryFileDTO(1, 10, "file.txt");
        BaseResponse baseResponse = storeFileServiceByMinioImpl.queryPage(queryFileDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(baseResponse.getData());
        System.out.println(json);
    }
    @Test
    public void testDeleteFile() throws Exception {
        minioUtil.deleteFile("202404072110401712495440645file.txt");
    }
}
