package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.ProductCategorySizesDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true")
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    private List<CategoryDto> categoriesDto;
    private List<SizeDto> sizesDto;
    private List<Color> colors;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    @BeforeEach
    void setUp() {
        categoriesDto = List.of(CategoryDto.builder().categoryId(3L).build());
        sizesDto = List.of(SizeDto.builder().sizeId(8L).build(),
                            SizeDto.builder().sizeId(9L).build(),
                            SizeDto.builder().sizeId(10L).build());
        colors = List.of(Color.BLACK);
        minPrice = BigDecimal.valueOf(700);
        maxPrice = BigDecimal.valueOf(1500);
    }

    @Test
    void testFindTenRandom() {
        //when
        List<ProductDto> tenRandomProductsDto = productService.findTenRandom();

        //then
        assertFalse(tenRandomProductsDto.isEmpty());
        assertEquals(10, tenRandomProductsDto.size());
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByAllFilters() {
        //prepare
        Long expectedCategoryId = 3L;
        Color expectedColor = Color.BLACK;
        List<Long> actualSizeIds = List.of(8L, 9L, 10L);
        int bottomPriceRange = minPrice.intValue();
        int topPriceRange = maxPrice.intValue();

        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(categoriesDto, colors, sizesDto, minPrice, maxPrice);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds =
                    filteredProduct.getCategory()
                                    .getSizes().stream()
                                                .map(SizeDto::getSizeId)
                                                .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.containsAll(actualSizeIds));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutCategories() {
        //prepare
        Color expectedColor = Color.BLACK;
        List<Long> actualSizeIds = List.of(8L, 9L, 10L);
        int bottomPriceRange = minPrice.intValue();
        int topPriceRange = maxPrice.intValue();

        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(null, colors, sizesDto, minPrice, maxPrice);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds =
                    filteredProduct.getCategory()
                                    .getSizes().stream()
                                                .map(SizeDto::getSizeId)
                                                .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertNotNull(categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.containsAll(actualSizeIds));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutColors() {
        //prepare
        Long expectedCategoryId = 3L;
        List<Long> actualSizeIds = List.of(8L, 9L, 10L);
        int bottomPriceRange = minPrice.intValue();
        int topPriceRange = maxPrice.intValue();

        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(categoriesDto, null, sizesDto, minPrice, maxPrice);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds =
                    filteredProduct.getCategory()
                                    .getSizes().stream()
                                                .map(SizeDto::getSizeId)
                                                .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertNotNull(colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.containsAll(actualSizeIds));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutSizes() {
        //prepare
        Long expectedCategoryId = 3L;
        Color expectedColor = Color.BLACK;
        int bottomPriceRange = minPrice.intValue();
        int topPriceRange = maxPrice.intValue();

        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(categoriesDto, colors, null, minPrice, maxPrice);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<SizeDto> filteredProductSizes = filteredProduct.getCategory().getSizes();
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertFalse(filteredProductSizes.isEmpty());
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutMinPrice() {
        //prepare
        Long expectedCategoryId = 3L;
        Color expectedColor = Color.BLACK;
        List<Long> actualSizeIds = List.of(8L, 9L, 10L);
        int topPriceRange = maxPrice.intValue();

        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(categoriesDto, colors, sizesDto, null, maxPrice);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds =
                    filteredProduct.getCategory()
                                    .getSizes().stream()
                                                .map(SizeDto::getSizeId)
                                                .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.containsAll(actualSizeIds));
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutMaxPrice() {
        //prepare
        Long expectedCategoryId = 3L;
        Color expectedColor = Color.BLACK;
        List<Long> actualSizeIds = List.of(8L, 9L, 10L);
        int bottomPriceRange = minPrice.intValue();

        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(categoriesDto, colors, sizesDto, minPrice, null);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds =
                    filteredProduct.getCategory()
                            .getSizes().stream()
                            .map(SizeDto::getSizeId)
                            .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.containsAll(actualSizeIds));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutCategoriesAndColorsAndMaxPrice() {
        //prepare
        List<Long> actualSizeIds = List.of(8L, 9L, 10L);
        int bottomPriceRange = minPrice.intValue();

        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(null, null, sizesDto, minPrice, null);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds =
                    filteredProduct.getCategory()
                            .getSizes().stream()
                            .map(SizeDto::getSizeId)
                            .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertNotNull(categoryIdOfFilteredProduct);
            assertNotNull(colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.containsAll(actualSizeIds));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutColorsAndSizesAndMinPrice() {
        //prepare
        Long expectedCategoryId = 3L;
        int topPriceRange = maxPrice.intValue();

        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(categoriesDto, null, null, null, maxPrice);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<SizeDto> filteredProductSizes = filteredProduct.getCategory().getSizes();
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertNotNull(colorOfFilteredProduct);
            assertFalse(filteredProductSizes.isEmpty());
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutAllFilters() {
        //when
        List<ProductCategorySizesDto> filteredProductsDto =
                productService.findAllByFilters(null, null, null, null, null);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<SizeDto> filteredProductSizes = filteredProduct.getCategory().getSizes();
            BigDecimal priceOfFilteredProduct = filteredProduct.getPrice();

            assertNotNull(categoryIdOfFilteredProduct);
            assertNotNull(colorOfFilteredProduct);
            assertFalse(filteredProductSizes.isEmpty());
            assertNotNull(priceOfFilteredProduct);
        }
    }
}