package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDto transformToDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .catalogType(category.getCatalogType())
                .build();
    }

    public static CategoryProductsDto transformToDtoWithProducts(Category category) {
        List<ProductDto> productsDto = category.getProducts().stream()
                                            .map(ProductMapper::transformToDto)
                                            .collect(Collectors.toList());
        List<SizeDto> sizesDto = category.getSizes().stream()
                                    .map(SizeMapper::transformToDto)
                                    .collect(Collectors.toList());

        return CategoryProductsDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .catalogType(category.getCatalogType())
                .sizes(sizesDto)
                .products(productsDto)
                .build();
    }

    public static Category transformToDao(CategoryDto categoryDto) {
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .catalogType(categoryDto.getCatalogType())
                .build();
    }
}
