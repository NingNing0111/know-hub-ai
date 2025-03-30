package me.pgthinker.core.utils;

import cn.hutool.crypto.digest.MD5;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @Project: me.pgthinker.utils
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/29 06:45
 * @Description:
 */
public class FileUtil {

	private static final Tika tika = new Tika();

	private static final String DEFAULT_MIME = "application/octet-stream";

	/**
	 * 生成一个ID
	 * @param bucketName
	 * @param objectName
	 * @return
	 */
	public static String generatorFileId(String bucketName, String objectName) {
		return MD5.create().digestHex16(bucketName + objectName);
	}

	/**
	 * 判断MimeType
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String detectMimeType(String path) throws IOException {
		Path p = Path.of(path);
		String mime = tika.detect(p.toFile());
		if (!DEFAULT_MIME.equals(mime)) {
			return mime;
		}
		mime = Files.probeContentType(p);
		if (mime != null) {
			return mime;
		}
		return tika.detect(p.getFileName().toString());
	}

	public static String detectMimeType(File file) throws IOException {
		return detectMimeType(file.getPath());
	}

	/**
	 * 下载URL资源到临时文件
	 * @param fileUrl 文件URL
	 * @param prefix 临时文件名前缀
	 * @param suffix 临时文件名后缀
	 * @return 临时文件对象
	 */
	public static File downloadToTempFile(String fileUrl, String prefix, String suffix) throws Exception {
		File tempFile = File.createTempFile(prefix, suffix);
		FileUtils.copyURLToFile(new URL(fileUrl), tempFile);
		return tempFile;
	}

	/**
	 * 下载到指定路径（NIO方式）
	 */
	public static void downloadToPath(String fileUrl, Path targetPath) throws Exception {
		try (InputStream in = new URL(fileUrl).openStream()) {
			Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	/**
	 * 创建临时文件
	 * @param prefix
	 * @param suffix
	 * @return
	 * @throws IOException
	 */
	public static File createTempFile(String prefix, String suffix) throws IOException {
		return File.createTempFile(prefix, suffix);
	}

	/**
	 * 读取一个文件 返回字符串
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String readFileAsString(File file) throws IOException {
		return Files.readString(file.toPath());
	}

	public static File writeToTempFile(String content, String prefix, String suffix) throws IOException {
		File tempFile = createTempFile(prefix, suffix);
		FileUtils.writeStringToFile(tempFile, content, Charset.defaultCharset());
		return tempFile;
	}

	/**
	 * 获取一个文件的md5值
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String md5(File file) throws IOException {
		return MD5.create().digestHex16(Files.readAllBytes(Path.of(file.getPath())));
	}

}
