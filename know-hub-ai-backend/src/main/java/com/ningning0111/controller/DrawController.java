package com.ningning0111.controller;

import cn.hutool.core.util.StrUtil;
import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.DrawImageDTO;
import com.ningning0111.model.dto.DrawOptions;
import com.ningning0111.service.DrawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/8 21:38
 * @Description:
 */
@Tag(name="DrawController",description = "绘画接口")
@RestController
@RequiredArgsConstructor
@RequestMapping(ApplicationConstant.API_VERSION + "/draw")
public class DrawController {
    private final DrawService drawService;
    private final Set<Integer> dall2Size = new HashSet<>(List.of(256,512,1024));
    private final Set<Integer> dall3Size = new HashSet<>(List.of(1024,1792));
    @Operation(summary = "draw",description = "绘画接口")
    @PostMapping("/")
    public BaseResponse drawImage(@RequestBody DrawImageDTO drawImageDTO){
        if(StrUtil.isEmptyIfStr(drawImageDTO.prompt())){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "图片描述为空");
        }
        DrawOptions options = drawImageDTO.options();
        if(!StrUtil.containsAny(options.format(),"url","b64_json")){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"不支持返回的图片格式" + options.format());
        }
        if(!StrUtil.containsAny(drawImageDTO.options().model(), OpenAiImageApi.ImageModel.DALL_E_2.getValue(),OpenAiImageApi.ImageModel.DALL_E_3.getValue())){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"不支持绘画模型:" + options.model());
        }
        Integer height = options.height();
        Integer width = options.width();
        if(StrUtil.equals(options.model(),OpenAiImageApi.ImageModel.DALL_E_2.getValue())){

            // height 或 width不在指定范围内 或者 高 或 宽在指定范围内 但不相等 则不支持
            if(dall2Size.contains(height) && !Objects.equals(height, width) || !dall2Size.contains(height) || !dall2Size.contains(width)){
                return ResultUtils.error(ErrorCode.PARAMS_ERROR,"图片尺寸不支持");
            }
        }
        if(StrUtil.equals(options.model(),OpenAiImageApi.ImageModel.DALL_E_3.getValue())){
            // height 或 width 不在指定范围内 或者 高宽相等并且等于1792 则大小不支持
            if(!dall3Size.contains(height) || !dall3Size.contains(width) || height == 1792 && height.equals(width)){
                return ResultUtils.error(ErrorCode.PARAMS_ERROR,"图片尺寸不支持");
            }
        }
        return drawService.drawImage(drawImageDTO);
    }

}
