package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.exception.BusinessException;
import com.ningning0111.model.entity.OneApi;
import com.ningning0111.service.OneApiService;
import com.ningning0111.service.StoreService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 16:53
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final OneApiService oneApiService;
    private final JdbcTemplate jdbcTemplate;
    private final TokenTextSplitter tokenTextSplitter;



    @Override
    public BaseResponse pdfStore(MultipartFile file) {
        try {
            Resource resource = saveFileToResource(file);
            VectorStore vectorStore = randomGetVectorStore();
            TikaDocumentReader tkReader = new TikaDocumentReader(resource);
            List<Document> documents = tkReader.get();
            vectorStore.accept(tokenTextSplitter.apply(documents));
            return ResultUtils.success("上传成功");
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(ErrorCode.OPERATION_ERROR,e.getMessage());
        }

    }

    @Override
    public BaseResponse txtStore(MultipartFile file) {
        return null;
    }

    @Override
    public BaseResponse excelStore(MultipartFile file) {
        return null;
    }

    @Override
    public BaseResponse htmlStore(MultipartFile file) {
        return null;
    }

    @Override
    public BaseResponse jsonStore(MultipartFile file) {
        return null;
    }



    @Override
    public VectorStore randomGetVectorStore(){
        OneApi oneApi = oneApiService.randomGetOne();
        OpenAiApi openAiApi = new OpenAiApi(oneApi.getBaseUrl(), oneApi.getApiKey());
        EmbeddingClient openAiEmbeddingClient = new OpenAiEmbeddingClient(openAiApi);
        return new PgVectorStore(jdbcTemplate,openAiEmbeddingClient);
    }

    private Resource saveFileToResource(MultipartFile file){
        try {
            String fileName = file.getName();
            byte[] bytes = file.getBytes();
            Path tempFile = Files.createTempFile("temp-", fileName);
            Files.write(tempFile,bytes);
            FileSystemResource fileResource = new FileSystemResource(tempFile.toFile());
            return fileResource;
        }catch (IOException e){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"文件处理异常");
        }
    }
}
