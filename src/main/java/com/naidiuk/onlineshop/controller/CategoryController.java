package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> categoriesDto = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categoriesDto);
    }
}
