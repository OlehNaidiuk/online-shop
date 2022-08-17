package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.CatalogType;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CategoryProductsDto {
    private Long categoryId;
    private String name;
    private CatalogType catalogType;
    @Builder.Default
    private List<SizeDto> sizes = new ArrayList<>();
    @Builder.Default
    private List<ProductDto> products = new ArrayList<>();
}
