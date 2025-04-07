package me.pgthinker.core.service.objectstore;

import me.pgthinker.core.utils.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * @Project: me.pgthinker.core.service.objectstore
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 16:38
 * @Description:
 */
public interface ObjectStoreService {

	/**
	 * 上传文件
	 * @param file 文件对象
	 * @param bucketName 存储桶名称
	 * @param objectName 对象名称
	 * @return 文件存储位置
	 */
	default String uploadFile(MultipartFile file, String bucketName, String objectName) throws IOException {
		InputStream inputStream = file.getInputStream();
		long fileSize = file.getSize();
		String contentType = file.getContentType();
		return uploadFile(inputStream, fileSize, bucketName, objectName, contentType);
	}

	/**
	 * 上传文件
	 * @param file 文件对象
	 * @param bucketName 存储桶名称
	 * @param objectName 对象名称
	 * @return 文件存储位置
	 */
	default String uploadFile(File file, String bucketName, String objectName) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		long fileSize = file.length();
		String contentType = FileUtil.detectMimeType(file);
		return uploadFile(inputStream, fileSize, bucketName, objectName, contentType);
	}

	/**
	 * 上传文件
	 * @param inputStream 文件流
	 * @param fileSize 文件大小
	 * @param bucketName 存储桶名称
	 * @param objectName 对象名称
	 * @param contentType 文件类型
	 * @return 文件存储位置
	 */
	String uploadFile(InputStream inputStream, long fileSize, String bucketName, String objectName, String contentType)
			throws IOException;

	/**
	 * 上传文件到默认存储桶
	 * @param file 文件对象
	 * @param objectName 对象名称
	 * @return 文件存储位置
	 */
	String uploadFile(MultipartFile file, String objectName);

	/**
	 * 获取临时的文件URL
	 * @param bucketName 存储桶名称
	 * @param objectName 对象名称
	 * @param expirySeconds URL有效期(秒)
	 * @return 文件访问URL
	 */
	String getTmpFileUrl(String bucketName, String objectName, int expirySeconds);

	/**
	 * 获取临时的文件URL 默认过期时间
	 * @param bucketName
	 * @param objectName
	 * @return
	 */
	String getTmpFileUrl(String bucketName, String objectName);

	/**
	 * 获取文件输入流
	 * @param bucketName 存储桶名称
	 * @param objectName 对象名称
	 * @return 文件输入流
	 */
	InputStream getFile(String bucketName, String objectName);

	/**
	 * 删除文件
	 * @param bucketName 存储桶名称
	 * @param objectName 对象名称
	 */
	void deleteFile(String bucketName, String objectName);

	/**
	 * 列出存储桶中的文件
	 * @param bucketName 存储桶名称
	 * @return 文件列表
	 */
	List<String> listFiles(String bucketName);

	/**
	 * 获取文件信息
	 * @param bucketName 存储桶名称
	 * @param objectName 对象名称
	 * @return 文件信息
	 */
	StorageFile getFileInfo(String bucketName, String objectName);

	/**
	 * 检查存储桶是否存在
	 * @param bucketName 存储桶名称
	 * @return 是否存在
	 */
	boolean bucketExists(String bucketName);

	/**
	 * 创建存储桶
	 * @param bucketName 存储桶名称
	 */
	void createBucket(String bucketName);

}
