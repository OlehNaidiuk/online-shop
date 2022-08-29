package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.CategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SaleDto;
import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.entity.Product;

public class ProductMapper {

    public static ProductDto transformToDto(Product product) {
        SaleDto saleDto = SaleMapper.transformToDto(product.getSale());

        return ProductDto.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .color(product.getColor())
                .name(product.getName())
                .description(product.getDescription())
                .male(product.getMale())
                .sale(saleDto)
                .build();
    }

    public static ProductCategorySizesDto transformToDtoWithCategoryAndCategorySizes(Product product) {
        Category category = product.getCategory();
        CategorySizesDto categorySizesDto = CategoryMapper.transformToDtoWithSizes(category);

        return ProductCategorySizesDto.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .color(product.getColor())
                .name(product.getName())
                .description(product.getDescription())
                .male(product.getMale())
                .category(categorySizesDto)
                .build();
    }
}
