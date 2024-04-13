package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.config.KnowHubAIConfig;
import com.ningning0111.exception.BusinessException;
import com.ningning0111.model.dto.QueryFileDTO;
import com.ningning0111.model.entity.OneApi;
import com.ningning0111.model.entity.StoreFile;
import com.ningning0111.repository.StoreFileRepository;
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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：何汉叁
 * @date ：2024/4/7 21:16
 * @description：知识库（Minio文件存储服务版）
 * 下次再写
 */
@Service("storeFileServiceByMinioImpl")
@Slf4j
@RequiredArgsConstructor
public class StoreFileServiceByMinioImpl implements StoreFileService {
    private final OneApiService oneApiService;
    private final JdbcTemplate jdbcTemplate;
    private final TokenTextSplitter tokenTextSplitter;
    private final StoreFileRepository storeFileRepository;
    private final KnowHubAIConfig knowHubAIConfig;
    private final MinioUtil minioUtil;
    @Override
    public BaseResponse queryPage(QueryFileDTO request) {
        Page<StoreFile> pageFile = storeFileRepository.findByFileNameContaining(request.fileName(), PageRequest.of(request.page(), request.pageSize()));
        return ResultUtils.success(pageFile);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public BaseResponse deleteFiles(List<Long> ids) {
        List<StoreFile> storeFiles = storeFileRepository.findAllById(ids);
        List<String> vectorIds = new LinkedList<>();
        for(StoreFile file: storeFiles){
            vectorIds.addAll(file.getVectorId());
        }
        VectorStore vectorStore = randomGetVectorStore();
        vectorStore.delete(vectorIds);
        storeFileRepository.deleteAllById(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public BaseResponse fileStore(MultipartFile file) {
        try {
            if (file == null){
                return ResultUtils.error(ErrorCode.FILE_ERROR);
            }
            Resource resource = file.getResource();
            VectorStore vectorStore = randomGetVectorStore();
            TikaDocumentReader tkReader = new TikaDocumentReader(resource);
            List<Document> documents = tkReader.get();
            List<Document> applyList = tokenTextSplitter.apply(documents);
            long currMillis = System.currentTimeMillis();
            vectorStore.accept(applyList);
            storeFileRepository.save(StoreFile.builder()
                    .fileName(resource.getFilename())
                    .vectorId(applyList.stream().map(Document::getId).collect(Collectors.toList()))
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