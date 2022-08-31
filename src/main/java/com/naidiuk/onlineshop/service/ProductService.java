package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Color;

import java.util.Currency;
import java.util.List;

public interface ProductService {
    List<ProductDto> findTenRandom();
    ProductDto convertPriceByCurrency(Currency currency, ProductDto productDto);
    List<ProductCategorySizesDto> findAllByFilters(List<Long> categoryIds,
                                                   List<Long> sizeIds,
                                                   List<Color> colors,
                                                   int minPrice,
                                                   int maxPrice);
}
