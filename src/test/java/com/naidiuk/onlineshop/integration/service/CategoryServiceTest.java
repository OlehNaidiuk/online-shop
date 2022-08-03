package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    void testFindAll() {
        //prepare
        List<CategoryDto> foundedCategoriesDto;

        //when
        foundedCategoriesDto = categoryService.findAll();

        //then
        assertFalse(foundedCategoriesDto.isEmpty());
    }
}