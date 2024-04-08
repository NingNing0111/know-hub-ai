package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.DrawImageDTO;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/8 21:07
 * @Description:
 */
public interface DrawService {
    BaseResponse drawImage(DrawImageDTO drawImageDTO);
}
