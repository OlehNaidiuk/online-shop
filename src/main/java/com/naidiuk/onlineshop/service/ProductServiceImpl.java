package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.mapper.ProductMapper;
import com.naidiuk.onlineshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                        .map(ProductMapper::transformToDto)
                        .collect(Collectors.toList());
    }
}
