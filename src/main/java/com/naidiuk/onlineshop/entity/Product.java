package com.naidiuk.onlineshop.entity;

import java.math.BigDecimal;
import java.util.Currency;

public class Product {
    private Long productId;
    private BigDecimal price;
    private Currency currency;
    private Color color;
    private String name;
    private String description;
    private Male male;
}
