package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
}
