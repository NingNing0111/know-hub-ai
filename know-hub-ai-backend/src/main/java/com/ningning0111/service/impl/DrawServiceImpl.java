package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.DrawImageDTO;
import com.ningning0111.model.dto.DrawOptions;
import com.ningning0111.model.entity.OneApi;
import com.ningning0111.service.DrawService;
import com.ningning0111.service.OneApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/8 21:08
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DrawServiceImpl implements DrawService {
    private final OneApiService oneApiService;
    @Override
    public BaseResponse drawImage(DrawImageDTO drawImageDTO) {
        ImageClient imageClient = imageClient();
        DrawOptions drawOptions = drawImageDTO.options();
        // 图片配置信息
        OpenAiImageOptions options = OpenAiImageOptions.builder()
                .withModel(drawOptions.model())
                .withHeight(drawOptions.height())
                .withWidth(drawOptions.width())
                .withResponseFormat(drawOptions.format())
                .build();
        // 绘制图片
        ImageResponse imageResponse = imageClient.call(new ImagePrompt(drawImageDTO.prompt(),options));
        return ResultUtils.success(imageResponse.getResults());

    }

    // 创建AI绘画客户端
    private ImageClient imageClient(){
        OneApi oneApi = oneApiService.randomGetOne();
        OpenAiImageApi openAiImageApi = new OpenAiImageApi(
                oneApi.getBaseUrl(),
                oneApi.getApiKey(),
                RestClient.builder()
        );
        return new OpenAiImageClient(openAiImageApi);
    }
}
