package com.ningning0111.controller;

import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @PostMapping("/pdf")
    @ResponseBody
    public BaseResponse addPdf(@RequestParam MultipartFile file){
        return storeService.pdfStore(file);
    }

    @PostMapping("/json")
    @ResponseBody
    public BaseResponse addJson(@RequestBody MultipartFile file){
        return storeService.jsonStore(file);
    }
}
