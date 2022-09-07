package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductFilterDto;
import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.mapper.ProductMapper;
import com.naidiuk.onlineshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .sale(productDto.getSale())
                .build();
    }

    @Override
    public List<ProductCategorySizesDto> findAllBy(ProductFilterDto productFilter) {
        List<Long> categoryIds = productFilter.getCategories();
        List<Long> sizeIds = productFilter.getSizes();
        List<Color> colors = productFilter.getColors();
        int minPrice = 0;
        if (productFilter.getMinPrice() != null) {
            minPrice = productFilter.getMinPrice();
        }
        int maxPrice = 100_000;
        if (productFilter.getMaxPrice() != null) {
            maxPrice = productFilter.getMaxPrice();
        }
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
}
