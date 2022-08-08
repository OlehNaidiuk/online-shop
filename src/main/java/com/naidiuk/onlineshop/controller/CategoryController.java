package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        List<CategoryDto> categoriesDto = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categoriesDto);
    }
}
