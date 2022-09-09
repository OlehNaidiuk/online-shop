package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.Color;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductFilterDto {
    private List<Long> categories;
    private List<Long> sizes;
    private List<Color> colors;
    private Integer minPrice;
    private Integer maxPrice;
    private String query;
}
