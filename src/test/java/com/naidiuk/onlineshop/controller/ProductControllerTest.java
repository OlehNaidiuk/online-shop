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

import static org.hamcrest.Matchers.hasSize;
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
        List<ProductDto> productsDto = createProductsDtoList();

        Mockito.when(productService.findAll()).thenReturn(productsDto);

        //then
        mockMvc.perform(get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").value(hasSize(11)))
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].price").value(1234.99))
                .andExpect(jsonPath("$[0].currency").value("UAH"))
                .andExpect(jsonPath("$[0].color").value("BLUE"))
                .andExpect(jsonPath("$[0].name").value("Сандалі HUGO Jens Sand"))
                .andExpect(
                        jsonPath("$[0].description")
                        .value("Сандалі із колекції HUGO. Модель виготовлена із текстильного матеріалу.")
                )
                .andExpect(jsonPath("$[0].male").value("MEN"));
    }

    @Test
    void testFindTenRandom() throws Exception {
        //prepare
        List<ProductDto> productsDto = createProductsDtoList();
        productsDto.remove(7);

        Mockito.when(productService.findTenRandom()).thenReturn(productsDto);

        //then
        mockMvc.perform(get("/api/v1/products/random").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").value(hasSize(10)));
    }

    private List<ProductDto> createProductsDtoList() {
        ProductDto first = new ProductDto();
        first.setProductId(1L);
        first.setPrice(new BigDecimal("1234.99"));
        first.setCurrency(Currency.getInstance("UAH"));
        first.setColor(Color.BLUE);
        first.setName("Сандалі HUGO Jens Sand");
        first.setDescription("Сандалі із колекції HUGO. Модель виготовлена із текстильного матеріалу.");
        first.setMale(Male.MEN);

        ProductDto second = new ProductDto();
        second.setProductId(2L);
        second.setPrice(new BigDecimal("200.99"));
        second.setCurrency(Currency.getInstance("UAH"));
        second.setColor(Color.GREEN);
        second.setName("Футболка HUGO Jens Sand");
        second.setDescription("Футболка із колекції HUGO. Модель виготовлена із текстильного матеріалу.");
        second.setMale(Male.MEN);

        ProductDto third = new ProductDto();
        third.setProductId(3L);
        third.setPrice(new BigDecimal("2345.99"));
        third.setCurrency(Currency.getInstance("UAH"));
        third.setColor(Color.BLACK);
        third.setName("Кофта HUGO Jens Sand");
        third.setDescription("Кофта із колекції HUGO. Модель виготовлена із еластичного трикотажу.");
        third.setMale(Male.WOMEN);

        ProductDto fourth = new ProductDto();
        fourth.setProductId(4L);
        fourth.setPrice(new BigDecimal("44.99"));
        fourth.setCurrency(Currency.getInstance("UAH"));
        fourth.setColor(Color.BLACK);
        fourth.setName("Джинси HUGO Jens Sand");
        fourth.setDescription("Джинси із колекції HUGO. Модель виготовлена із еластичного трикотажу.");
        fourth.setMale(Male.WOMEN);

        ProductDto fifth = new ProductDto();
        fifth.setProductId(5L);
        fifth.setPrice(new BigDecimal("788.99"));
        fifth.setCurrency(Currency.getInstance("UAH"));
        fifth.setColor(Color.BLUE);
        fifth.setName("Сандалі HUGO Jens Sand");
        fifth.setDescription("Сандалі із колекції HUGO. Модель виготовлена із еластичного трикотажу.");
        fifth.setMale(Male.MEN);

        ProductDto sixth = new ProductDto();
        sixth.setProductId(6L);
        sixth.setPrice(new BigDecimal("433.99"));
        sixth.setCurrency(Currency.getInstance("UAH"));
        sixth.setColor(Color.GREEN);
        sixth.setName("Футболка HUGO Jens Sand");
        sixth.setDescription("Футболка із колекції HUGO. Модель виготовлена із текстильного матеріалу.");
        sixth.setMale(Male.MEN);

        ProductDto seventh = new ProductDto();
        seventh.setProductId(7L);
        seventh.setPrice(new BigDecimal("1500.99"));
        seventh.setCurrency(Currency.getInstance("UAH"));
        seventh.setColor(Color.BLACK);
        seventh.setName("Кросівки HUGO Jens Sand");
        seventh.setDescription("Кросівки із колекції HUGO. Модель виготовлена із текстильного матеріалу.");
        seventh.setMale(Male.WOMEN);

        ProductDto eighth = new ProductDto();
        eighth.setProductId(8L);
        eighth.setPrice(new BigDecimal("1200.99"));
        eighth.setCurrency(Currency.getInstance("UAH"));
        eighth.setColor(Color.BLACK);
        eighth.setName("Джинси HUGO Jens Sand");
        eighth.setDescription("Джинси із колекції HUGO. Модель виготовлена із еластичного трикотажу.");
        eighth.setMale(Male.WOMEN);

        ProductDto ninth = new ProductDto();
        ninth.setProductId(9L);
        ninth.setPrice(new BigDecimal("10000.99"));
        ninth.setCurrency(Currency.getInstance("UAH"));
        ninth.setColor(Color.PINK);
        ninth.setName("Куртка BOSS");
        ninth.setDescription("Куртка із колекції BOSS. Неутеплена модель виготовлена з деніму.");
        ninth.setMale(Male.MEN);

        ProductDto tenth = new ProductDto();
        tenth.setProductId(10L);
        tenth.setPrice(new BigDecimal("12999.99"));
        tenth.setCurrency(Currency.getInstance("UAH"));
        tenth.setColor(Color.BLACK);
        tenth.setName("Куртка Emporio Armani");
        tenth.setDescription("Куртка із колекції Emporio Armani. Перехідна модель виготовленаз гладкого матеріалу.");
        tenth.setMale(Male.WOMEN);

        ProductDto eleventh = new ProductDto();
        eleventh.setProductId(11L);
        eleventh.setPrice(new BigDecimal("3499.99"));
        eleventh.setCurrency(Currency.getInstance("UAH"));
        eleventh.setColor(Color.BLUE);
        eleventh.setName("Бігові кросівки Reebok Zig Dynamica 3");
        eleventh.setDescription("Черевики для бігу з колекції Reebok. Модель забезпечує стопи амортизацією під час активності.");
        eleventh.setMale(Male.WOMEN);

        List<ProductDto> productsDto = new ArrayList<>(11);
        productsDto.add(first);
        productsDto.add(second);
        productsDto.add(third);
        productsDto.add(fourth);
        productsDto.add(fifth);
        productsDto.add(sixth);
        productsDto.add(seventh);
        productsDto.add(eighth);
        productsDto.add(ninth);
        productsDto.add(tenth);
        productsDto.add(eleventh);

        return productsDto;
    }
}