package com.ningning0111.utils;

import cn.hutool.core.date.DateUtil;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ：何汉叁、NingNing0111
 * @date ：2024/4/7 13:19
 * @description：Minio工具类
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioUtil {
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.access-key}")
    private String ACCESS_KEY;
    @Value("${minio.secret-key}")
    private String SECRET_KEY;
    @Value("${minio.bucket-name}")
    private String BUCKET_NAME;
    private MinioClient minioClient;


    /**
     * 初始化minio客户端
     */
    @PostConstruct
    public void init() {
        // 如果开启了minio，则初始化minio客户端
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

    /**
     * 判断bucket是否存在
     */
    @SneakyThrows(Exception.class)
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建bucket
     */
    @SneakyThrows(Exception.class)
    public void createBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取全部bucket
     */
    @SneakyThrows(Exception.class)
    public List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    /**
     * 上传文件
     * 返回可以直接预览文件的URL
     */
    public String uploadFile(MultipartFile file) {
        try {
            //如果存储桶不存在则创建
            if (!bucketExists(BUCKET_NAME)) {
                createBucket(BUCKET_NAME);
            }
            //保证文件不重名
            String fileName = file.getOriginalFilename();
            String objectName = fileName + "-" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
            return getPreviewFileUrl(objectName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件异常: 【{}】", e.fillInStackTrace());
            return "行不行啊！你这个文件";
        }
    }

    /**
     * 删除文件
     */
    @SneakyThrows(Exception.class)
    public void deleteFile(String fileName) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(fileName).build());
    }


    /**
     * 获取minio文件的下载或者预览地址
     * 取决于调用本方法的方法中的PutObjectOptions对象有没有设置contentType
     *
     * @param fileName: 文件名
     */
    @SneakyThrows(Exception.class)
    public String getPreviewFileUrl(String fileName) {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(BUCKET_NAME)
                        .object(fileName)
                        .expiry(24, TimeUnit.HOURS)
                        .build());
    }
}


