package com.ningning0111.repository;

import com.ningning0111.model.entity.Logs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {
    Page<Logs> findAll(Pageable pageable);
    long count();
}
