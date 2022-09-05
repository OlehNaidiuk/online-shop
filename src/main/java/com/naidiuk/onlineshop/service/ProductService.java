package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.*;
import com.naidiuk.onlineshop.entity.Product;

import java.util.Currency;
import java.util.List;

public interface ProductService {
    List<ProductDto> findTenRandom();
    ProductDto convertPriceByCurrency(Currency currency, ProductDto productDto);
    List<ProductCategorySizesDto> findAllBy(ProductFilterDto productFilter);
    ProductReviewsDto saveProductReview(Long productId, ReviewDto reviewDto);
    ProductReviewsDto deleteProductReview(Long productId, Long reviewId);
}
