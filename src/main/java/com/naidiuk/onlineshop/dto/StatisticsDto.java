package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatisticsDto {
    private long views;
    private ProductDto productDto;
}
