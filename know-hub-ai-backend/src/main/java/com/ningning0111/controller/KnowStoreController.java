package com.ningning0111.controller;

import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.UploadFileRequest;
import com.ningning0111.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping(ApplicationConstant.API_VERSION + "/know")
@RequiredArgsConstructor
public class KnowStoreController {

    private final StoreService storeService;

    @PostMapping(value="/file/upload",headers = "content-type=multipart/form-data")
    @ResponseBody
    public BaseResponse addPdf(@RequestParam("file") List<MultipartFile> file){

        return storeService.filesStore(file);
    }


}
