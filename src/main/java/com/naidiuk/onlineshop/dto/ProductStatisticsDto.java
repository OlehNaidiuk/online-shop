package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductStatisticsDto {
    private long productViews;
    private ProductDto productDto;
}
