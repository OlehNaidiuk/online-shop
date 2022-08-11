package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Product;

public class ProductMapper {
    public static ProductDto transformToDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .color(product.getColor())
                .name(product.getName())
                .description(product.getDescription())
                .male(product.getMale())
                .category(product.getCategory())
                .company(product.getCompany())
                .sale(product.getSale())
                .reviews(product.getReviews())
                .build();
    }
}
