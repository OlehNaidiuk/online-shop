package com.naidiuk.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CatalogType {
    CLOTHES("одяг"),
    FOOTWEAR("взуття"),
    ACCESSORIES("аксесуари");

    @Getter
    private final String translation;
}
