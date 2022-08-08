package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Getter
@Builder
public class ProductDto {
    private Long productId;
    private BigDecimal price;
    private Currency currency;
    private Color color;
    private String name;
    private String description;
    private Male male;
    private Category category;
    private Company company;
    private Sale sale;
    private List<Review> reviews;
}
