package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryProductsDto findById(Long categoryId);
}
