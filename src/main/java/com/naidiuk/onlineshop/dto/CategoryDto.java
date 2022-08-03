package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.CatalogType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryDto {
    private Long categoryId;
    private String name;
    private CatalogType catalogType;
}
