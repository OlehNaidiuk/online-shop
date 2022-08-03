package com.naidiuk.onlineshop.integration.controller;

import com.naidiuk.onlineshop.controller.ProductController;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    void testFindTenRandom() throws Exception {
        //prepare
        List<ProductDto> mockedProductsDto = createMockedList();
        doReturn(mockedProductsDto).when(productService).findTenRandom();

        //then
        mockMvc.perform(get("/api/v1/products/random")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").value(hasSize(mockedProductsDto.size())));
    }

    private List<ProductDto> createMockedList() {
        ProductDto firstMock = ProductDto.builder().build();
        ProductDto secondMock = ProductDto.builder().build();
        ProductDto thirdMock = ProductDto.builder().build();
        ProductDto fourthMock = ProductDto.builder().build();
        ProductDto fifthMock = ProductDto.builder().build();
        ProductDto sixthMock = ProductDto.builder().build();
        ProductDto seventhMock = ProductDto.builder().build();
        ProductDto eightsMock = ProductDto.builder().build();
        ProductDto ninthMock = ProductDto.builder().build();
        ProductDto tenthMock = ProductDto.builder().build();

        List<ProductDto> mockedList = new ArrayList<>(10);
        mockedList.add(firstMock);
        mockedList.add(secondMock);
        mockedList.add(thirdMock);
        mockedList.add(fourthMock);
        mockedList.add(fifthMock);
        mockedList.add(sixthMock);
        mockedList.add(seventhMock);
        mockedList.add(eightsMock);
        mockedList.add(ninthMock);
        mockedList.add(tenthMock);

        return mockedList;
    }
}