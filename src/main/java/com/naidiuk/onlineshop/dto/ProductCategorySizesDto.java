package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Male;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Builder
public class ProductCategorySizesDto {
    private Long productId;
    private BigDecimal price;
    private Currency currency;
    private Color color;
    private String name;
    private String description;
    private Male male;
    private CategorySizesDto category;
    private SaleDto sale;
}
