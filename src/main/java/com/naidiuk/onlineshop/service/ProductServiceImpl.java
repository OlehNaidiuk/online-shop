package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.mapper.ProductMapper;
import com.naidiuk.onlineshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.*;

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
                .sale(productDto.getSale())
                .build();
    }

    @Override
    public List<ProductCategorySizesDto> findAllByFilters(List<Long> categoryIds,
                                                          List<Long> sizeIds,
                                                          List<Color> colors,
                                                          int minPrice,
                                                          int maxPrice) {

        List<Product> products = productRepository.findAll(
                                                where(filterByCategories(categoryIds))
                                                    .and(filterBySizes(sizeIds))
                                                    .and(filterByColors(colors))
                                                    .and(filterByPriceRange(minPrice, maxPrice)));
        return products.stream()
                .map(ProductMapper::transformToDtoWithCategoryAndSizes)
                .collect(Collectors.toUnmodifiableList());
    }

    private Specification<Product> filterByCategories(List<Long> categoryIds) {
        if (categoryIds == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.in(root.get("category").get("categoryId")).value(categoryIds);
    }

    private Specification<Product> filterBySizes(List<Long> sizeIds) {
        if (sizeIds == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return criteriaBuilder.in(root.join("sizes", JoinType.LEFT).get("sizeId")).value(sizeIds);
        };
    }

    private Specification<Product> filterByColors(List<Color> colors) {
        if (colors == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get("color")).value(colors);

    }

    private Specification<Product> filterByPriceRange(int minPrice, int maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }
}
