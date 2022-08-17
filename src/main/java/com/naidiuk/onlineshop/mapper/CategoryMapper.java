package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.entity.Category;

public class CategoryMapper {

    public static CategoryDto transformToDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .catalogType(category.getCatalogType())
                .build();
    }
}
