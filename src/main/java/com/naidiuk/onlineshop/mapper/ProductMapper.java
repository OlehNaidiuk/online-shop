package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Product;

public class ProductMapper {
    public static ProductDto transformToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setPrice(product.getPrice());
        productDto.setCurrency(product.getCurrency());
        productDto.setColor(product.getColor());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setMale(product.getMale());
        return productDto;
    }

    public static Product transformToDao(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setPrice(productDto.getPrice());
        product.setCurrency(productDto.getCurrency());
        product.setColor(productDto.getColor());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setMale(productDto.getMale());
        return product;
    }
}
