package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void testFindTenRandom() {
        //when
        List<ProductDto> tenRandomProductsDto = productService.findTenRandom();

        //then
        assertFalse(tenRandomProductsDto.isEmpty());
        assertEquals(10, tenRandomProductsDto.size());
    }
}