package com.naidiuk.onlineshop.entity;

public enum Catalog {
    CLOTHES("одяг"),
    FOOTWEAR("взуття"),
    ACCESSORIES("аксесуари");

    private final String translation;

    Catalog(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
