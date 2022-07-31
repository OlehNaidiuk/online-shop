package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.mapper.ProductMapper;
import com.naidiuk.onlineshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    @Override
    public List<ProductDto> findTenRandom() {
        List<Product> products = productRepository.findAll();
        Random random = new Random();
        List<Product> tenRandomProducts = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(products.size());
            tenRandomProducts.add(products.get(index));
        }
        return tenRandomProducts.stream()
                                .map(ProductMapper::transformToDto)
                                .collect(Collectors.toList());
    }
}
