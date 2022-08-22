package com.naidiuk.onlineshop.modular.service;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.repository.ProductRepository;
import com.naidiuk.onlineshop.service.CurrencyConverterService;
import com.naidiuk.onlineshop.service.ProductService;
import com.naidiuk.onlineshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository mockedProductRepository;
    @Mock
    private CurrencyConverterService mockedCurrencyConverterService;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(mockedProductRepository, mockedCurrencyConverterService);
    }

    @Test
    void testFindTenRandom() {
        //prepare
        List<Product> mockedProducts = createMockedList();

        doReturn(mockedProducts).when(mockedProductRepository).findTenRandom();

        //when
        List<ProductDto> tenRandomProducts = productService.findTenRandom();

        //then
        assertFalse(tenRandomProducts.isEmpty());
        assertEquals(10, tenRandomProducts.size());
        verify(mockedProductRepository, times(1)).findTenRandom();
    }

    private List<Product> createMockedList() {
        Product firstMock = mock(Product.class);
        Product secondMock = mock(Product.class);
        Product thirdMock = mock(Product.class);
        Product fourthMock = mock(Product.class);
        Product fifthMock = mock(Product.class);
        Product sixthMock = mock(Product.class);
        Product seventhMock = mock(Product.class);
        Product eightsMock = mock(Product.class);
        Product ninthMock = mock(Product.class);
        Product tenthMock = mock(Product.class);

        List<Product> mockedList = new ArrayList<>(10);
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