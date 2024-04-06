package com.ningning0111.repository;

import com.ningning0111.model.entity.StoreFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project: com.ningning0111.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/5 20:01
 * @Description:
 */
@Repository
public interface StoreFileRepository extends JpaRepository<StoreFile,Long> {
    // 根据文件名分页模糊查询
    Page<StoreFile> findByFileNameContaining(String keyword, Pageable pageable);
}
