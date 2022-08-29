package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.entity.Color;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public interface ProductService {
    List<ProductDto> findTenRandom();
    ProductDto convertPriceByCurrency(Currency currency, ProductDto productDto);

    List<ProductCategorySizesDto> findAllByFilters(List<CategoryDto> categoriesDto,
                                                   List<Color> colors,
                                                   List<SizeDto> sizesDto,
                                                   BigDecimal minPrice,
                                                   BigDecimal maxPrice);
}
