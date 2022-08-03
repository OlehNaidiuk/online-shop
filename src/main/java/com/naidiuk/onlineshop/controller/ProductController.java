package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/random")
    public ResponseEntity<List<ProductDto>> findTenRandom() {
        List<ProductDto> productsDto = productService.findTenRandom();
        return ResponseEntity.status(HttpStatus.OK).body(productsDto);
    }
}
