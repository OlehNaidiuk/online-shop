package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductAllDto;
import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.ProductFilterDto;

import java.util.Currency;
import java.util.List;

public interface ProductService {
    List<ProductDto> findTenRandom();
    ProductDto convertPriceByCurrency(Currency currency, ProductDto productDto);
    List<ProductCategorySizesDto> findAllBy(ProductFilterDto productFilter);
    ProductAllDto save(ProductAllDto productAllDto);
    ProductDto update(ProductDto productDtoToUpdate);
    ProductDto deleteById(Long productId);
}
