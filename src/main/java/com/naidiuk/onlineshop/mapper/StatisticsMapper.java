package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.StatisticsDto;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.Statistics;

public class StatisticsMapper {
    private StatisticsMapper() {
    }

    public static StatisticsDto transformToDto(Statistics statistics) {
        Product product = statistics.getProduct();
        ProductDto productDto = ProductMapper.transformToDto(product);

        return StatisticsDto.builder()
                .views(statistics.getViews())
                .productDto(productDto)
                .build();
    }
}
