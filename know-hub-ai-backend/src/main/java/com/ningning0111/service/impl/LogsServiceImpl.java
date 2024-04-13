package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.entity.Logs;
import com.ningning0111.repository.LogsRepository;
import com.ningning0111.service.LogsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author ：何汉叁
 * @date ：2024/4/10 19:39
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LogsServiceImpl implements LogsService {
    private final LogsRepository logsRepository;

    @Override
    public BaseResponse listPager(int page, int size) {
        Page<Logs> logsPage = logsRepository.findAll(PageRequest.of(page, size));
        return ResultUtils.success(logsPage);
    }

    @Override
    public BaseResponse count() {
        return ResultUtils.success(logsRepository.count()) ;
    }

    @Override
    public boolean save(Logs logs) {
        Logs savedLogs = logsRepository.save(logs);
        return savedLogs != null && savedLogs.getId() != null;
    }
}
