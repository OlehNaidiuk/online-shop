package com.naidiuk.onlineshop.integration.controller;

import com.naidiuk.onlineshop.controller.CategoryController;
import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;

    @Test
    void testFindAll() throws Exception {
        //prepare
        CategoryDto firsDto = new CategoryDto();
        CategoryDto secondDto = new CategoryDto();
        CategoryDto thirdDto = new CategoryDto();
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
}