package com.ningning0111.service;

import com.ningning0111.repository.OneApiRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 18:50
 * @Description:
 */
@SpringBootTest
public class StoreServiceTest {
    @Autowired
    private StoreService service;

    @Autowired
    private OneApiRepository oneApiRepository;

    @Test
    public void test1() {
        oneApiRepository.deleteById(1L);
    }
}
