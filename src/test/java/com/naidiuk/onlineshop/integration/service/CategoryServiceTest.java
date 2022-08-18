package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.error.CategoryNotFoundException;
import com.naidiuk.onlineshop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldGetCategoryWithSizesAndProductsWhenFindByCategoryId() {
        //prepare
        Long categoryId = 7L;

        //when
        CategoryProductsDto categoryProductsDto = categoryService.findById(categoryId);
        List<SizeDto> sizesDto = categoryProductsDto.getSizes();
        List<ProductDto> productsDto = categoryProductsDto.getProducts();

        //then
        assertNotNull(categoryProductsDto);
        assertFalse(sizesDto.isEmpty());
        assertFalse(productsDto.isEmpty());
    }

    @Test
    void shouldThrowsCategoryNotFoundExceptionWhenFindByWrongCategoryId() {
        //prepare
        Long wrongCategoryId = 12L;

        //then
        assertThrows(CategoryNotFoundException.class, () -> categoryService.findById(wrongCategoryId));
    }
}