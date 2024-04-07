package com.ningning0111.controller;

import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.QueryFileDTO;
import com.ningning0111.service.StoreFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 18:53
 * @Description:
 */
@Tag(name="KnowStoreController",description = "知识库文件存储接口")
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/know")
@RequiredArgsConstructor
@Slf4j
public class KnowStoreController {

    private final StoreFileService storeFileService;
    @Operation(summary="文件上传",description = "文件上传")
    @PostMapping(value="/file/upload",headers = "content-type=multipart/form-data")
    public BaseResponse addPdf(@RequestParam("file") List<MultipartFile> file){

        return storeFileService.filesStore(file);
    }
    @Operation(summary = "文件查询",description = "文件查询")
    @GetMapping("/contents")
    public BaseResponse queryFiles(QueryFileDTO request){
        log.error("{}",request.fileName());
        return storeFileService.queryPage(request);
    }
    @Operation(summary = "文件删除",description = "文件删除")
    @DeleteMapping("/delete")
    public BaseResponse deleteFiles(@RequestParam List<Long> ids){
        return storeFileService.deleteFiles(ids);
    }


}
