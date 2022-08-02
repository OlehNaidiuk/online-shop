package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.Catalog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private Long categoryId;
    private String name;
    private Catalog catalog;
}
