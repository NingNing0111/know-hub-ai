package com.ningning0111.controller;

import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.QueryFileDTO;
import com.ningning0111.service.StoreFileService;
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
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/know")
@RequiredArgsConstructor
@Slf4j
public class KnowStoreController {

    private final StoreFileService storeFileService;

    @PostMapping(value="/file/upload",headers = "content-type=multipart/form-data")
    public BaseResponse addPdf(@RequestParam("file") List<MultipartFile> file){

        return storeFileService.filesStore(file);
    }

    @GetMapping("/contents")
    public BaseResponse queryFiles(QueryFileDTO request){
        log.error("{}",request.fileName());
        return storeFileService.queryPage(request);
    }

    @DeleteMapping("/delete")
    public BaseResponse deleteFiles(@RequestParam List<Long> ids){
        return storeFileService.deleteFiles(ids);
    }


}
