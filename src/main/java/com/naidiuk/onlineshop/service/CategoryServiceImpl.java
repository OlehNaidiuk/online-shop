package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;
import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.error.CategoryNotFoundException;
import com.naidiuk.onlineshop.mapper.CategoryMapper;
import com.naidiuk.onlineshop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public CategoryProductsDto findById(Long categoryId) {
        Category category = categoryRepository.findByIdWithProducts(categoryId)
                                        .orElseThrow(() ->
                                                new CategoryNotFoundException(
                                                        String.format("Category with id=%d not found.", categoryId)));
        return CategoryMapper.transformToDtoWithProducts(category);
    }
}
