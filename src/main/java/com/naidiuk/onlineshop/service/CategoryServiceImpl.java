package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;
import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.error.CategoryNotFoundException;
import com.naidiuk.onlineshop.mapper.CategoryMapper;
import com.naidiuk.onlineshop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                        .map(CategoryMapper::transformToDto)
                        .collect(Collectors.toList());
    }

    @Override
    public CategoryProductsDto findById(Long categoryId) {
        String message = String.format("Category with id=%d not found.", categoryId);
        Category category = categoryRepository.findById(categoryId)
                                            .orElseThrow(() -> new CategoryNotFoundException(message));
        return CategoryMapper.transformToDtoWithProducts(category);
    }
}
