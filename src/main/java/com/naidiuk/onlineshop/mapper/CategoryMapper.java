package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
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
}
