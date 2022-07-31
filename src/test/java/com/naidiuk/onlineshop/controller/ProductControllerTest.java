package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Male;
import com.naidiuk.onlineshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    void testFindAll() throws Exception {
        //prepare
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setPrice(new BigDecimal("1299.99"));
        productDto.setCurrency(Currency.getInstance("UAH"));
        productDto.setColor(Color.BLACK);
        productDto.setName("Джинси HUGO Jens Sand");
        productDto.setDescription("Джинси із колекції HUGO. Модель виготовлена із еластичного трикотажу.");
        productDto.setMale(Male.WOMEN);

        List<ProductDto> productsDto = new ArrayList<>();
        productsDto.add(productDto);

        Mockito.when(productService.findAll()).thenReturn(productsDto);

        //then
        mockMvc.perform(get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].price").value(1299.99))
                .andExpect(jsonPath("$[0].currency").value("UAH"))
                .andExpect(jsonPath("$[0].color").value("BLACK"))
                .andExpect(jsonPath("$[0].name").value("Джинси HUGO Jens Sand"))
                .andExpect(jsonPath("$[0].description")
                        .value("Джинси із колекції HUGO. Модель виготовлена із еластичного трикотажу."))
                .andExpect(jsonPath("$[0].male").value("WOMEN"));
    }
}