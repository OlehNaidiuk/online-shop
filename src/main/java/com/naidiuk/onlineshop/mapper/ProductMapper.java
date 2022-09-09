package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.*;
import com.naidiuk.onlineshop.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto transformToDto(Product product) {
        SaleDto saleDto = SaleMapper.transformToDto(product.getSale());

        return ProductDto.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .color(product.getColor())
                .name(product.getName())
                .description(product.getDescription())
                .male(product.getMale())
                .sale(saleDto)
                .build();
    }

    public static ProductCategorySizesDto transformToDtoWithCategoryAndSizes(Product product) {
        CategoryDto categoryDto = CategoryMapper.transformToDto(product.getCategory());
        List<SizeDto> sizesDto = product.getSizes().stream()
                                    .map(SizeMapper::transformToDto)
                                    .collect(Collectors.toUnmodifiableList());
        SaleDto saleDto = SaleMapper.transformToDto(product.getSale());

        return ProductCategorySizesDto.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .color(product.getColor())
                .name(product.getName())
                .description(product.getDescription())
                .male(product.getMale())
                .category(categoryDto)
                .sizes(sizesDto)
                .sale(saleDto)
                .build();
    }

    public static ProductReviewsDto transformToDtoWithReviews(Product product) {
        List<ReviewDto> reviewsDto = product.getReviews().stream()
                                .map(ReviewMapper::transformToDto)
                                .collect(Collectors.toUnmodifiableList());

        return ProductReviewsDto.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .color(product.getColor())
                .name(product.getName())
                .description(product.getDescription())
                .male(product.getMale())
                .reviews(reviewsDto)
                .build();
    }

    public static ProductAllDto transformToAllDto(Product product) {
        CategoryDto categoryDto = CategoryMapper.transformToDto(product.getCategory());
        CompanyDto companyDto = CompanyMapper.transformToDto(product.getCompany());
        List<SizeDto> sizesDto = product.getSizes().stream()
                .map(SizeMapper::transformToDto)
                .collect(Collectors.toUnmodifiableList());
        SaleDto saleDto = SaleMapper.transformToDto(product.getSale());

        return ProductAllDto.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .color(product.getColor())
                .name(product.getName())
                .description(product.getDescription())
                .male(product.getMale())
                .category(categoryDto)
                .sizes(sizesDto)
                .company(companyDto)
                .sale(saleDto)
                .build();
    }

    public static Product transformToDao(ProductAllDto productAllDto) {
        Category category = CategoryMapper.transformToDao(productAllDto.getCategory());
        Company company = CompanyMapper.transformToDao(productAllDto.getCompany());
        List<Size> sizes = productAllDto.getSizes().stream()
                                .map(SizeMapper::transformToDao)
                                .collect(Collectors.toUnmodifiableList());
        Sale sale = SaleMapper.transformToDao(productAllDto.getSale());

        return Product.builder()
                .productId(productAllDto.getProductId())
                .price(productAllDto.getPrice())
                .currency(productAllDto.getCurrency())
                .color(productAllDto.getColor())
                .name(productAllDto.getName())
                .description(productAllDto.getDescription())
                .male(productAllDto.getMale())
                .category(category)
                .sizes(sizes)
                .company(company)
                .sale(sale)
                .build();
    }
}
