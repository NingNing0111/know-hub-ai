package com.ningning0111.repository;

import com.ningning0111.model.entity.MinioFile;
import com.ningning0111.model.entity.StoreFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：何汉叁
 * @date ：2024/4/10 21:31
 * @description：TODO
 */
@Repository
public interface MinioFileRepository extends JpaRepository<MinioFile, Long> {
    Page<MinioFile> findByFileNameContaining(String keyword, Pageable pageable);
}
