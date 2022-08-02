package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.entity.Category;

public class CategoryMapper {
    public static CategoryDto transformToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setName(category.getName());
        categoryDto.setCatalog(category.getCatalog());
        return categoryDto;
    }
}
