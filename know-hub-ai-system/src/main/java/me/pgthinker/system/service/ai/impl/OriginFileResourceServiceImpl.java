package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.core.common.CoreCode;
import me.pgthinker.core.exception.BusinessException;
import me.pgthinker.core.service.objectstore.ObjectStoreService;
import me.pgthinker.core.service.objectstore.StorageFile;
import me.pgthinker.core.utils.FileUtil;
import me.pgthinker.system.mapper.OriginFileResourceMapper;
import me.pgthinker.system.model.entity.ai.OriginFileResource;
import me.pgthinker.system.model.entity.user.SystemUser;
import me.pgthinker.system.service.ai.OriginFileResourceService;
import me.pgthinker.system.utils.SecurityFrameworkUtil;
import org.springframework.ai.model.Media;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
* @author pgthinker
* @description 针对表【origin_file_source(存储原始文件资源的表)】的数据库操作Service实现
* @createDate 2025-04-08 04:47:02
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class OriginFileResourceServiceImpl extends ServiceImpl<OriginFileResourceMapper, OriginFileResource>
    implements OriginFileResourceService {

    public static final String BUCKET_NAME = "OriginFile";

    private ObjectStoreService objectStoreService;

    @Override
    public List<Media> fromResourceId(List<String> resourceIds) {
        return List.of();
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String objectName = objectNameWithUserId(originalFilename);
        String id = FileUtil.generatorFileId(BUCKET_NAME, objectName);
        String newObjectName = String.format("%s/%s", id, objectName);
        String path;
        String md5;
        try {
            md5 = FileUtil.md5(file.getResource().getFile());
            path = objectStoreService.uploadFile(file, BUCKET_NAME, newObjectName);
        }catch (IOException e) {
            throw new BusinessException(CoreCode.SYSTEM_ERROR, e.getMessage());
        }
        StorageFile fileInfo = objectStoreService.getFileInfo(BUCKET_NAME, newObjectName);
        OriginFileResource originFileResource = new OriginFileResource();
        originFileResource.setMd5(md5);
        originFileResource.setFileName(originalFilename);
        originFileResource.setPath(path);
        originFileResource.setId(fileInfo.getId());
        originFileResource.setBucketName(BUCKET_NAME);
        originFileResource.setObjectName(newObjectName);
        originFileResource.setIsImage(file.getContentType() != null && file.getContentType().startsWith("image"));
        originFileResource.setSize(fileInfo.getSize());
        originFileResource.setContentType(fileInfo.getContentType());
        this.saveOrUpdate(originFileResource);
        return originFileResource.getId();
    }

    @Override
    public String uploadFile(MultipartFile file, String knowledgeId) {
        return "";
    }

    private String objectNameWithUserId(String filename) {
        SystemUser loginUser = SecurityFrameworkUtil.getLoginUser();
        return loginUser.getId() + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + filename;
    }


}




