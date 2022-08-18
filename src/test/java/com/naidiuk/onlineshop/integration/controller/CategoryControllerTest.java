package com.naidiuk.onlineshop.integration.controller;

import com.naidiuk.onlineshop.controller.CategoryController;
import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.error.CategoryNotFoundException;
import com.naidiuk.onlineshop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    void testFindAll() throws Exception {
        //prepare
        CategoryDto firsDto = CategoryDto.builder().build();
        CategoryDto secondDto = CategoryDto.builder().build();
        CategoryDto thirdDto = CategoryDto.builder().build();

        List<CategoryDto> categoriesDto = new ArrayList<>();
        categoriesDto.add(firsDto);
        categoriesDto.add(secondDto);
        categoriesDto.add(thirdDto);

        doReturn(categoriesDto).when(categoryService).findAll();

        //then
        mockMvc.perform(get("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").value(hasSize(categoriesDto.size())));
    }

    @Test
    void shouldReturnCategoryWithSizesAndProductsWhenFindByCategoryId() throws Exception {
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

        doReturn(categoryProductsDto).when(categoryService).findById(categoryId);

        //then
        mockMvc.perform(get("/api/v1/categories/{id}/products", categoryId))
                .andDo(print())
                .andExpect(jsonPath("$.categoryId").value(categoryId))
                .andExpect(jsonPath("$.sizes").value(hasSize(sizesDto.size())))
                .andExpect(jsonPath("$.products").value(hasSize(productsDto.size())));
    }

    @Test
    void shouldReturnErrorResponseWhenFindByWrongCategoryId() throws Exception {
        //prepare
        Long wrongCategoryId = 12L;
        String message = String.format("Category with id=%d not found.", wrongCategoryId);
        CategoryNotFoundException categoryNotFoundException = new CategoryNotFoundException(message);

        doThrow(categoryNotFoundException).when(categoryService).findById(wrongCategoryId);

        //then
        mockMvc.perform(get("/api/v1/categories/{id}/products", wrongCategoryId))
                .andDo(print())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value(message));
    }
}