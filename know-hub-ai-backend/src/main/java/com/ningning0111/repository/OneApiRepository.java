package com.ningning0111.repository;

import com.ningning0111.model.entity.OneApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project: com.ningning0111.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 17:08
 * @Description:
 */
@Repository
public interface OneApiRepository extends JpaRepository<OneApi,Long> {
    // 查询所有未禁用的Key
    List<OneApi> findAllByDisableIsFalse();
    List<OneApi> findAllByDisableIsFalse(Pageable pageable);
}
