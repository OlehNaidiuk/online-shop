package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
}
