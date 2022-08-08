package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.CatalogType;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.Size;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CategoryDto {
    private Long categoryId;
    private String name;
    private CatalogType catalogType;
    private List<Size> sizes;
    private List<Product> products;
}
