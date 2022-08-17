package com.naidiuk.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Male;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long productId;
    private BigDecimal price;
    private Currency currency;
    private Color color;
    private String name;
    private String description;
    private Male male;
    private SaleDto sale;
}
