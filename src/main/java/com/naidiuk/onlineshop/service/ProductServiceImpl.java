package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.Size;
import com.naidiuk.onlineshop.mapper.CategoryMapper;
import com.naidiuk.onlineshop.mapper.ProductMapper;
import com.naidiuk.onlineshop.mapper.SizeMapper;
import com.naidiuk.onlineshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ProductCategorySizesDto> findAllByFilters(List<CategoryDto> categoriesDto,
                                                          List<Color> colors,
                                                          List<SizeDto> sizesDto,
                                                          BigDecimal minPrice,
                                                          BigDecimal maxPrice) {

        List<Product> products;
        if (categoriesDto != null && !categoriesDto.isEmpty()) {
            if (colors != null && !colors.isEmpty()) {
                if (sizesDto != null && !sizesDto.isEmpty()) {
                    if (minPrice != null) {
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByColors(colors))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByPriceRange(minPrice, maxPrice)));
                        } else {
                            // (categories && colors && sizes) are not empty, minPrice != null, maxPrice == null
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByColors(colors))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByMinPrice(minPrice)));
                        }
                    } else {
                        // (categories && colors && sizes) are not empty, minPrice == null
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByColors(colors))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByMaxPrice(maxPrice)));
                        } else {
                            // (categories && colors && sizes) are not empty, (minPrice && maxPrice) == null
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByColors(colors))
                                            .and(filterBySizes(sizesDto)));
                        }
                    }
                } else {
                    // sizes is empty, (categories && colors) are not empty
                    if (minPrice != null) {
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByColors(colors))
                                            .and(filterByPriceRange(minPrice, maxPrice)));
                        } else {
                            // sizes is empty, (categories && colors) are not empty, minPrice != null, maxPrice == null
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByColors(colors))
                                            .and(filterByMinPrice(minPrice)));
                        }
                    } else {
                        // sizes is empty, (categories && colors) are not empty, minPrice == null
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByColors(colors))
                                            .and(filterByMaxPrice(maxPrice)));
                        } else {
                            // sizes is empty, (categories && colors) are not empty, (minPrice && maxPrice) == null
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByColors(colors)));
                        }
                    }
                }
            } else {
                // colors is empty, categories not empty
                if (sizesDto != null && !sizesDto.isEmpty()) {
                    if (minPrice != null) {
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByPriceRange(minPrice, maxPrice)));
                        } else {
                            // colors is empty, (categories && sizes) not empty, maxPrice == null
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByMinPrice(minPrice)));
                        }
                    } else {
                        // colors is empty, (categories && sizes) not empty, minPrice == null
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByMaxPrice(maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterBySizes(sizesDto)));
                        }
                    }
                } else {
                    // (colors && sizes) are empty, categories not empty
                    if (minPrice != null) {
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByPriceRange(minPrice, maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByMinPrice(minPrice)));
                        }
                    } else {
                        // (colors && sizes) are empty, categories not empty, minPrice == null
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto))
                                            .and(filterByMaxPrice(maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterByCategories(categoriesDto)));
                        }
                    }
                }
            }
        } else {
            // categories is empty
            if (colors != null && !colors.isEmpty()) {
                if (sizesDto != null && !sizesDto.isEmpty()) {
                    if (minPrice != null) {
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByColors(colors))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByPriceRange(minPrice, maxPrice)));
                        } else {
                            // categories is empty, (colors && sizes) are not empty, minPrice != null, maxPrice == null
                            products = productRepository.findAll(
                                    where(filterByColors(colors))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByMinPrice(minPrice)));
                        }
                    } else {
                        // categories is empty, (colors && sizes) are not empty, minPrice == null
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByColors(colors))
                                            .and(filterBySizes(sizesDto))
                                            .and(filterByMaxPrice(maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterByColors(colors))
                                            .and(filterBySizes(sizesDto)));
                        }
                    }
                } else {
                    // (categories && sizes) are empty, colors not empty
                    if (minPrice != null) {
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByColors(colors))
                                            .and(filterByPriceRange(minPrice, maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterByColors(colors))
                                            .and(filterByMinPrice(minPrice)));
                        }
                    } else {
                        // (categories && sizes) are empty, colors not empty, minPrice == null
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByColors(colors))
                                            .and(filterByMaxPrice(maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterByColors(colors)));
                        }
                    }
                }
            } else {
                // (categories && colors) are empty
                if (sizesDto != null && !sizesDto.isEmpty()) {
                    // (categories && colors) are empty, sizes not empty
                    if (minPrice != null) {
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterBySizes(sizesDto))
                                            .and(filterByPriceRange(minPrice, maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterBySizes(sizesDto))
                                            .and(filterByMinPrice(minPrice)));
                        }
                    } else {
                        // (categories && colors) are empty, sizes not empty, minPrice == null
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterBySizes(sizesDto))
                                            .and(filterByMaxPrice(maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterBySizes(sizesDto)));
                        }
                    }
                } else {
                    // (categories && colors && sizes) are empty
                    if (minPrice != null) {
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByPriceRange(minPrice, maxPrice)));
                        } else {
                            products = productRepository.findAll(
                                    where(filterByMinPrice(minPrice)));
                        }
                    } else {
                        // (categories && colors && sizes) are empty, minPrice == null
                        if (maxPrice != null) {
                            products = productRepository.findAll(
                                    where(filterByMaxPrice(maxPrice)));
                        } else {
                            // (categories && colors && sizes) are empty, (minPrice && maxPrice) == null
                            products = productRepository.findAll();
                        }
                    }
                }
            }
        }

        return products.stream()
                .map(ProductMapper::transformToDtoWithCategoryAndCategorySizes)
                .collect(Collectors.toUnmodifiableList());
    }

    private Specification<Product> filterByCategories(List<CategoryDto> categoriesDto) {
        List<Category> categories = categoriesDto.stream()
                                                .map(CategoryMapper::transformToDao)
                                                .collect(Collectors.toUnmodifiableList());
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.in(root.get("category")).value(categories);
    }

    private Specification<Product> filterBySizes(List<SizeDto> sizesDto) {
        List<Size> sizes = sizesDto.stream()
                                .map(SizeMapper::transformToDao)
                                .collect(Collectors.toUnmodifiableList());
        return (root, query, criteriaBuilder) -> {

            query.distinct(true);
            return criteriaBuilder.in(
                                        root.join("category", JoinType.LEFT)
                                            .join("sizes", JoinType.LEFT))
                                .value(sizes);
        };
    }

    private Specification<Product> filterByColors(List<Color> colors) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get("color")).value(colors);

    }

    private Specification<Product> filterByMinPrice(BigDecimal minPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private Specification<Product> filterByMaxPrice(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private Specification<Product> filterByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }
}
