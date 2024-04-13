package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.entity.Logs;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/4/10 19:39
 * @description：TODO
 */

public interface LogsService {

        BaseResponse listPager(int page, int size);

        BaseResponse count();

        boolean save(Logs logs);
}
