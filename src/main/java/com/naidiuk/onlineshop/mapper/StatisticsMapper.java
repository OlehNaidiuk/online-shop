package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.ProductStatisticsDto;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.ProductStatistics;

public class StatisticsMapper {
    private StatisticsMapper() {
    }

    public static ProductStatisticsDto transformToDto(ProductStatistics productStatistics) {
        Product product = productStatistics.getProduct();
        ProductDto productDto = ProductMapper.transformToDto(product);

        return ProductStatisticsDto.builder()
                .productViews(productStatistics.getProductViews())
                .productDto(productDto)
                .build();
    }
}
