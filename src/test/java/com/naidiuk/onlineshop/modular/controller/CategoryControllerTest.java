package com.naidiuk.onlineshop.modular.controller;

import com.naidiuk.onlineshop.controller.CategoryController;
import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.error.CategoryNotFoundException;
import com.naidiuk.onlineshop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    @Mock
    private CategoryService mockedCategoryService;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private CategoryController categoryController;

    @Test
    void testFindAll() {
        //prepare
        List<CategoryDto> categoriesDto = new ArrayList<>();
        CategoryDto firstDto = mock(CategoryDto.class);
        categoriesDto.add(firstDto);
        
        doReturn(categoriesDto).when(mockedCategoryService).findAll();
        
        //when
        ResponseEntity<List<CategoryDto>> responseEntity = categoryController.findAll(request);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categoriesDto, responseEntity.getBody());
        verify(mockedCategoryService, times(1)).findAll();
    }

    @Test
    void shouldReturnCategoryWithSizesAndProductsWhenFindByCategoryId() {
        //prepare
        SizeDto sizeDto = SizeDto.builder().build();
        ProductDto productDto = ProductDto.builder().build();

        List<SizeDto> sizesDto = new ArrayList<>();
        sizesDto.add(sizeDto);
        List<ProductDto> productsDto = new ArrayList<>();
        productsDto.add(productDto);

        Long categoryId = 7L;
        CategoryProductsDto categoryProductsDto = CategoryProductsDto.builder()
                                                                    .categoryId(categoryId)
                                                                    .sizes(sizesDto)
                                                                    .products(productsDto)
                                                                    .build();

        doReturn(categoryProductsDto).when(mockedCategoryService).findById(categoryId);

        //when
        ResponseEntity<?> responseEntity = categoryController.findAllProductsByCategoryId(categoryId, request);

        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categoryProductsDto, responseEntity.getBody());
        verify(mockedCategoryService, times(1)).findById(categoryId);
    }

    @Test
    void shouldThrowsCategoryNotFoundExceptionWhenFindByWrongCategoryId() {
        //prepare
        Long wrongCategoryId = 12L;
        String message = String.format("Category with id=%d not found.", wrongCategoryId);
        CategoryNotFoundException categoryNotFoundException = new CategoryNotFoundException(message);

        doThrow(categoryNotFoundException).when(mockedCategoryService).findById(wrongCategoryId);

        //then
        assertThrows(CategoryNotFoundException.class
                , () -> categoryController.findAllProductsByCategoryId(wrongCategoryId, request));
    }
}