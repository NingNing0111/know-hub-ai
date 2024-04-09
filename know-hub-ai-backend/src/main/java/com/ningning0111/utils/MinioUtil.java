package com.ningning0111.utils;

import cn.hutool.core.date.DateUtil;
import com.ningning0111.config.KnowHubAIConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ：何汉叁
 * @date ：2024/4/7 13:19
 * @description：Minio工具类
 */

@Slf4j
@Component
@Lazy // 懒加载 需要用到时 才注入
@RequiredArgsConstructor
public class MinioUtil {
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.access-key}")
    private String ACCESS_KEY;
    @Value("${minio.secret-key}")
    private String SECRET_KEY ;
    private final KnowHubAIConfig knowHubAIConfig;
    public static String BUCKET_NAME ;
    public static MinioClient minioClient;
    @Value("${minio.bucket-name}")
    public void setBucketName(String bucketName) {
        BUCKET_NAME = bucketName;
    }

    /**
     * 初始化minio客户端
     */
    @PostConstruct
    public void init() {
        // 如果开启了minio，则初始化minio客户端
        if(knowHubAIConfig.isMinioEnabled()){
            try {
                log.info("Minio Initialize........................");
                minioClient = MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
                createBucket(BUCKET_NAME);
                log.info("Minio Initialize........................successful");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("初始化minio配置异常: 【{}】", e.fillInStackTrace());
            }
        }
    }
    /**
     * 判断bucket是否存在
     */
    @SneakyThrows(Exception.class)
    public static boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建bucket
     */
    @SneakyThrows(Exception.class)
    public static void createBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取全部bucket
     */
    @SneakyThrows(Exception.class)
    public static List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    /**
     * 上传文件
     * 返回可以直接预览文件的URL
     */
    public static String uploadFile(MultipartFile file) {
        try {
            //如果存储桶不存在则创建
            if (!bucketExists(BUCKET_NAME)) {
                createBucket(BUCKET_NAME);
            }
            //保证文件不重名
            String fileName = file.getName();
            String timeRandom = String.valueOf(System.currentTimeMillis());
            fileName = timeRandom + fileName;
            String objectName = DateUtil.format(new Date(), "yyyyMMddHHmmss") + fileName;
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                    .build());
            return getPreviewFileUrl(BUCKET_NAME, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件异常: 【{}】", e.fillInStackTrace());
            return "行不行啊！你这个文件";
        }
    }

    /**
     * 删除文件
     *
     */
    @SneakyThrows(Exception.class)
    public static void deleteFile(String fileName) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(fileName).build());
    }


    /**
     * 获取minio文件的下载或者预览地址
     * 取决于调用本方法的方法中的PutObjectOptions对象有没有设置contentType
     *
     * @param bucketName: 桶名
     * @param fileName:   文件名
     */
    @SneakyThrows(Exception.class)
    public static String getPreviewFileUrl(String bucketName, String fileName) {
        Map<String, String> reqParams = new HashMap<String, String>();
        reqParams.put("response-content-type", "application/json");
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(BUCKET_NAME)
                        .object(fileName)
                        .expiry(24, TimeUnit.HOURS)
                        .extraQueryParams(reqParams)
                        .build());
    }
}


