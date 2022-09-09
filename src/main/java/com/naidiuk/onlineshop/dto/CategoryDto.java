package com.naidiuk.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.naidiuk.onlineshop.entity.CatalogType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {
    private Long categoryId;
    private String name;
    private CatalogType catalogType;
}
