package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductDto;

import java.util.Currency;
import java.util.List;

public interface ProductService {
    List<ProductDto> findTenRandom();
    ProductDto convertPriceByCurrency(Currency currency, ProductDto productDto);
}
