package com.naidiuk.onlineshop.modular.controller;

import com.naidiuk.onlineshop.controller.ProductController;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.service.ProductService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService mockedProductService;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private ProductController productController;

    @Test
    void testFindTenRandom() {
        //prepare
        List<ProductDto> productsDto = new ArrayList<>();
        doReturn(productsDto).when(mockedProductService).findTenRandom();

        //when
        ResponseEntity<List<ProductDto>> responseEntity = productController.findTenRandom(request);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productsDto, responseEntity.getBody());
        verify(mockedProductService, times(1)).findTenRandom();
    }
}