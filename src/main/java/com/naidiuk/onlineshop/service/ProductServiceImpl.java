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
    private final StatisticsService statisticsService;

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
                .sale(productDto.getSale())
                .build();
    }

    @Override
    public List<ProductCategorySizesDto> findAllBy(ProductFilterDto productFilter) {
        List<Long> categoryIds = productFilter.getCategories();
        List<Long> sizeIds = productFilter.getSizes();
        List<Color> colors = productFilter.getColors();
        Integer minPrice = productFilter.getMinPrice();
        Integer maxPrice = productFilter.getMaxPrice();
        String query = productFilter.getQuery();

        List<Product> products = productRepository.findAll(
                                    where(filterByCategories(categoryIds))
                                            .and(filterBySizes(sizeIds))
                                            .and(filterByColors(colors))
                                            .and(filterByPriceRange(minPrice, maxPrice))
                                            .and(filterByQuery(query)));

        return products.stream()
                .map(ProductMapper::transformToDtoWithCategoryAndSizes)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ProductReviewsDto saveProductReview(Long productId, ReviewDto reviewDto) {
        Product product = findByIdWithReviews(productId);
        Review reviewToAdd = ReviewMapper.transformToDao(reviewDto);
        product.addReview(reviewToAdd);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.transformToDtoWithReviews(savedProduct);
    }

    @Override
    @Transactional
    public ProductReviewsDto deleteProductReview(Long productId, Long reviewId) {
        Product product = findByIdWithReviews(productId);
        Review reviewToDelete = product.getReviews().stream()
                                        .filter(review -> (review.getReviewId()).equals(reviewId))
                                        .findFirst()
                                        .orElseThrow(() ->
                                                new ReviewNotFoundException(
                                                        String.format("Review with id=%d not found", reviewId)));
        product.removeReview(reviewToDelete);
        return ProductMapper.transformToDtoWithReviews(product);
    }

    @Override
    public ProductAllDto save(ProductAllDto productAllDto) {
        Product product = ProductMapper.transformToDao(productAllDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.transformToAllDto(savedProduct);
    }

    @Override
    public ProductDto findById(Long productId) {
        Product product = findOne(productId);
        statisticsService.incrementProductView(product);
        return ProductMapper.transformToDto(product);
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDtoToUpdate) {
        Long productId = productDtoToUpdate.getProductId();
        Product product = findOne(productId);
        product.setPrice(productDtoToUpdate.getPrice());
        product.setCurrency(productDtoToUpdate.getCurrency());
        product.setColor(productDtoToUpdate.getColor());
        product.setName(productDtoToUpdate.getName());
        product.setDescription(productDtoToUpdate.getDescription());
        product.setMale(productDtoToUpdate.getMale());
        return ProductMapper.transformToDto(product);
    }

    @Override
    public ProductDto deleteById(Long productId) {
        Product product = findOne(productId);
        ProductDto productDto = ProductMapper.transformToDto(product);
        productRepository.deleteById(productId);
        return productDto;
    }

    private Product findByIdWithReviews(Long productId) {
        return productRepository.findByIdWithReviews(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                String.format("Product with id=%d not found", productId)));
    }

    private Product findOne(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                String.format("Product with id=%d not found.", productId)));
    }
}
