package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.*;
import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.Size;

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
        List<Product> products = category.getProducts();
        List<ProductDto> productsDto = products.stream()
                                            .map(ProductMapper::transformToDto)
                                            .collect(Collectors.toList());
        List<Size> sizes = category.getSizes();
        List<SizeDto> sizesDto = sizes.stream()
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

    public static CategorySizesDto transformToDtoWithSizes(Category category) {
        List<Size> sizes = category.getSizes();
        List<SizeDto> sizesDto = sizes.stream()
                                    .map(SizeMapper::transformToDto)
                                    .collect(Collectors.toList());

        return CategorySizesDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .catalogType(category.getCatalogType())
                .sizes(sizesDto)
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
