package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.*;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.Review;
import com.naidiuk.onlineshop.error.ProductNotFoundException;
import com.naidiuk.onlineshop.error.ReviewNotFoundException;
import com.naidiuk.onlineshop.mapper.ProductMapper;
import com.naidiuk.onlineshop.mapper.ReviewMapper;
import com.naidiuk.onlineshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import static com.naidiuk.onlineshop.repository.specification.ProductSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CurrencyConverterService currencyConverterService;

    @Override
    public List<ProductDto> findTenRandom() {
        List<Product> tenRandomProducts = productRepository.findTenRandom();
        return tenRandomProducts.stream()
                .map(ProductMapper::transformToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto convertPriceByCurrency(Currency currency, ProductDto productDto) {
        BigDecimal productPriceByCurrency = currencyConverterService.convertTo(currency, productDto.getPrice());

        return ProductDto.builder()
                .productId(productDto.getProductId())
                .price(productPriceByCurrency)
                .currency(currency)
                .color(productDto.getColor())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .male(productDto.getMale())
                .build();
    }

    @Override
    public List<ProductCategorySizesDto> findAllBy(ProductFilterDto productFilter) {
        List<Long> categoryIds = productFilter.getCategories();
        List<Long> sizeIds = productFilter.getSizes();
        List<Color> colors = productFilter.getColors();
        int minPrice = productFilter.getMinPrice();
        int maxPrice = productFilter.getMaxPrice();

        List<Product> products = productRepository.findAll(
                                    where(filterByCategories(categoryIds))
                                            .and(filterBySizes(sizeIds))
                                            .and(filterByColors(colors))
                                            .and(filterByPriceRange(minPrice, maxPrice)));

        return products.stream()
                .map(ProductMapper::transformToDtoWithCategoryAndSizes)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ProductReviewsDto saveProductReview(Long productId, ReviewDto reviewDto) {
        Product product = findByIdWithReviews(productId);
        Review productReview = ReviewMapper.transformToDao(reviewDto);
        productReview.setProduct(product);
        product.getReviews().add(productReview);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.transformToDtoWithReviews(savedProduct);
    }

    @Override
    @Transactional
    public ProductReviewsDto deleteProductReview(Long productId, Long reviewId) {
        Product product = findByIdWithReviews(productId);
        Review reviewToBeDeleted = product.getReviews().stream()
                                        .filter(review -> (review.getReviewId()).equals(reviewId))
                                        .findFirst()
                                        .orElseThrow(() ->
                                                new ReviewNotFoundException(
                                                        String.format("Review with id=%d not found", reviewId)));
        product.getReviews().remove(reviewToBeDeleted);
        reviewToBeDeleted.setProduct(null);
        return ProductMapper.transformToDtoWithReviews(product);
    }

    private Product findByIdWithReviews(Long productId) {
        return productRepository.findByIdWithReviews(productId)
                            .orElseThrow(() ->
                                    new ProductNotFoundException(
                                            String.format("Product with id=%d not found", productId)));
    }
}
