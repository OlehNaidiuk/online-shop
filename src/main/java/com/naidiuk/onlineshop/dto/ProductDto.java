package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Male;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Setter
@Getter
public class ProductDto {
    private Long productId;
    private BigDecimal price;
    private Currency currency;
    private Color color;
    private String name;
    private String description;
    private Male male;
}
