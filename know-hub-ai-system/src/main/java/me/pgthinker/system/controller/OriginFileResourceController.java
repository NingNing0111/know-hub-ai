package me.pgthinker.system.controller;

import lombok.RequiredArgsConstructor;
import me.pgthinker.core.common.BaseResponse;
import me.pgthinker.core.common.ResultUtils;
import me.pgthinker.system.service.ai.OriginFileResourceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: me.pgthinker.system.controller
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 05:14
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class OriginFileResourceController {

    private final OriginFileResourceService originFileResourceService;

    @PostMapping(value = "/chat",headers = "content-type=multipart/form-data")
    public BaseResponse<String> chatFile(@RequestParam(name = "file") MultipartFile file) {
        return ResultUtils.success(originFileResourceService.uploadFile(file));
    }

    @PostMapping(value = "/knowledge/{knowledgeId}", headers = "content-type=multipart/form-data")
    public BaseResponse<Long> uploadFile(@RequestParam(name = "file") MultipartFile file, @PathVariable(name = "knowledgeId") String knowledgeId ) {
        return ResultUtils.success(originFileResourceService.uploadFile(file, knowledgeId));
    }
}
