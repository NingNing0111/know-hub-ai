package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.exception.BusinessException;
import com.ningning0111.model.dto.QueryFileDTO;
import com.ningning0111.model.entity.MinioFile;
import com.ningning0111.model.entity.OneApi;
import com.ningning0111.repository.MinioFileRepository;
import com.ningning0111.service.OneApiService;
import com.ningning0111.service.StoreFileService;
import com.ningning0111.utils.MinioUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.ningning0111.utils.MatchUtil;

/**
 * @author ：何汉叁、NingNing0111
 * @date ：2024/4/7 21:16
 * @description：知识库（Minio文件存储服务版）
 * 获取文件功能未加
 */
@Service()
@Slf4j
@RequiredArgsConstructor
public class StoreFileServiceByMinioImpl implements StoreFileService {
    private final OneApiService oneApiService;
    private final JdbcTemplate jdbcTemplate;
    private final TokenTextSplitter tokenTextSplitter;
    private final MinioFileRepository minioRepository;
    private final MinioUtil minioUtil;

    /**
     * 查询文件
     * @param request
     * @return
     */
    @Override
    public BaseResponse queryPage(QueryFileDTO request) {
        Page<MinioFile> fileList = minioRepository.findByFileNameContaining(request.fileName(), PageRequest.of(request.page(), request.pageSize()));
        return ResultUtils.success(fileList);
    }

    /**
     * 删除指定ID列表的所有文件
     * @param ids
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public BaseResponse deleteFiles(List<Long> ids) {
        List<MinioFile> minioFiles = minioRepository.findAllById(ids);
        minioRepository.deleteAllById(ids);

        VectorStore vectorStore = randomGetVectorStore();

        for(MinioFile file: minioFiles){
            String minioFileName = MatchUtil.getMinioFileName(file.getUrl());
            minioUtil.deleteFile(minioFileName);
            vectorStore.delete(file.getVectorId());

        }
        return ResultUtils.success("删除成功");
    }

    @Override
    public BaseResponse fileStore(MultipartFile file) {
        try {
            Resource resource = file.getResource();
            VectorStore vectorStore = randomGetVectorStore();
            TikaDocumentReader tkReader = new TikaDocumentReader(resource);
            List<Document> documents = tkReader.get();
            List<Document> applyList = tokenTextSplitter.apply(documents);
            vectorStore.accept(applyList);

            String name = file.getOriginalFilename();
            String url = minioUtil.uploadFile(file);
            log.info(url);
            long currMillis = System.currentTimeMillis();
            minioRepository.save(MinioFile.builder()
                    .fileName(name)
                    .vectorId(applyList.stream().map(Document::getId).collect(Collectors.toList()))
                    .url(url)
                    .createTime(new Date(currMillis))
                    .updateTime(new Date(currMillis))
                    .build());
            return ResultUtils.success("上传成功");
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(ErrorCode.OPERATION_ERROR,e.getMessage());
        }

    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public BaseResponse filesStore(List<MultipartFile> files) {
        try {
            for (MultipartFile file :
                    files) {
                fileStore(file);
            }
            return ResultUtils.success("上传成功");
        }catch (Exception e){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,e.getMessage());
        }
    }

    @Override
    public VectorStore randomGetVectorStore(){
        OneApi oneApi = oneApiService.randomGetOne();
        OpenAiApi openAiApi = new OpenAiApi(oneApi.getBaseUrl(), oneApi.getApiKey());
        EmbeddingClient openAiEmbeddingClient = new OpenAiEmbeddingClient(openAiApi);
        return new PgVectorStore(jdbcTemplate,openAiEmbeddingClient);
    }
}