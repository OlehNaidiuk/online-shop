package com.naidiuk.onlineshop.modular.controller;

import com.naidiuk.onlineshop.controller.CategoryController;
import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    @Mock
    private CategoryService mockedCategoryService;
    @InjectMocks
    private CategoryController categoryController;

    @Test
    void findAll() {
        //prepare
        List<CategoryDto> categoriesDto = new ArrayList<>();
        CategoryDto firstDto = mock(CategoryDto.class);
        categoriesDto.add(firstDto);
        
        doReturn(categoriesDto).when(mockedCategoryService).findAll();
        
        //when
        ResponseEntity<List<CategoryDto>> responseEntity = categoryController.findAll();

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categoriesDto, responseEntity.getBody());
        verify(mockedCategoryService, times(1)).findAll();
    }
}