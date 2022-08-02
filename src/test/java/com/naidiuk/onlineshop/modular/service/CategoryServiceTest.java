package com.naidiuk.onlineshop.modular.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.repository.CategoryRepository;
import com.naidiuk.onlineshop.service.CategoryService;
import com.naidiuk.onlineshop.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository mockedCategoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(mockedCategoryRepository);
    }

    @Test
    void findAll() {
        //prepare
        List<Category> categories = new ArrayList<>();
        Category first = mock(Category.class);
        categories.add(first);

        doReturn(categories).when(mockedCategoryRepository).findAll();

        //when
        List<CategoryDto> categoriesDto = categoryService.findAll();

        //then
        assertNotNull(categoriesDto);
        assertEquals(1, categoriesDto.size());
        verify(mockedCategoryRepository, times(1)).findAll();
    }
}