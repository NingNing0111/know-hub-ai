package me.pgthinker.core.service.objectstore;

/**
 * @Project: me.pgthinker.service.objectstore
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/29 02:34
 * @Description:
 */
public interface StorageFile {

	String getId();

	String getBucketName();

	String getObjectName();

	String getContentType();

	String getFileName();

	String getPath();

	Long getSize();

	String getMd5();

}
