package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void testFindAll() {
        //when
        List<ProductDto> products = productService.findAll();
        int expectedSize = 72;

        //then
        assertFalse(products.isEmpty());
        assertEquals(expectedSize, products.size());
    }

    @Test
    void testFindTenRandom() {
        //when
        List<ProductDto> products = productService.findTenRandom();
        int expectedSize = 10;

        //then
        assertFalse(products.isEmpty());
        assertEquals(expectedSize, products.size());
    }
}