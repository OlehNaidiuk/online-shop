package com.naidiuk.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Male;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductAllDto {
    private Long productId;
    private BigDecimal price;
    private Currency currency;
    private Color color;
    private String name;
    private String description;
    private Male male;
    private CategoryDto category;
    @Builder.Default
    private List<SizeDto> sizes = new ArrayList<>();
    private CompanyDto company;
    private SaleDto sale;
    @Builder.Default
    private List<ReviewDto> reviews = new ArrayList<>();
}
