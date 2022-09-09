package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.ProductFilterDto;
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

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    private List<Long> categoryIds;
    private List<Long> sizeIds;
    private List<Color> colors;
    private int minPrice;
    private int maxPrice;

    @BeforeEach
    void setUp() {
        categoryIds = List.of(3L);
        sizeIds = List.of(8L, 9L, 10L);
        colors = List.of(Color.BLACK);
        minPrice = 700;
        maxPrice = 1500;
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
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                .categories(categoryIds)
                                                .colors(colors)
                                                .sizes(sizeIds)
                                                .minPrice(minPrice)
                                                .maxPrice(maxPrice)
                                                .build();

        Long expectedCategoryId = 3L;
        Color expectedColor = Color.BLACK;
        List<Long> actualSizeIds = sizeIds;
        int bottomPriceRange = minPrice;
        int topPriceRange = maxPrice;

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds = filteredProduct.getSizes().stream()
                                                            .map(SizeDto::getSizeId)
                                                            .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.stream()
                                .anyMatch(sizeId -> actualSizeIds.contains(sizeId)));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutCategories() {
        //prepare
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                .colors(colors)
                                                .sizes(sizeIds)
                                                .minPrice(minPrice)
                                                .maxPrice(maxPrice)
                                                .build();

        Color expectedColor = Color.BLACK;
        List<Long> actualSizeIds = sizeIds;
        int bottomPriceRange = minPrice;
        int topPriceRange = maxPrice;

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds = filteredProduct.getSizes().stream()
                                                            .map(SizeDto::getSizeId)
                                                            .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertNotNull(categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.stream()
                                .anyMatch(sizeId -> actualSizeIds.contains(sizeId)));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutColors() {
        //prepare
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                .categories(categoryIds)
                                                .sizes(sizeIds)
                                                .minPrice(minPrice)
                                                .maxPrice(maxPrice)
                                                .build();

        Long expectedCategoryId = 3L;
        List<Long> actualSizeIds = sizeIds;
        int bottomPriceRange = minPrice;
        int topPriceRange = maxPrice;

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds = filteredProduct.getSizes().stream()
                                                            .map(SizeDto::getSizeId)
                                                            .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertNotNull(colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.stream()
                                .anyMatch(sizeId -> actualSizeIds.contains(sizeId)));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutSizes() {
        //prepare
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                .categories(categoryIds)
                                                .colors(colors)
                                                .minPrice(minPrice)
                                                .maxPrice(maxPrice)
                                                .build();

        Long expectedCategoryId = 3L;
        Color expectedColor = Color.BLACK;
        int bottomPriceRange = minPrice;
        int topPriceRange = maxPrice;

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<SizeDto> filteredProductSizes = filteredProduct.getSizes();
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
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                .categories(categoryIds)
                                                .colors(colors)
                                                .sizes(sizeIds)
                                                .maxPrice(maxPrice)
                                                .build();

        Long expectedCategoryId = 3L;
        Color expectedColor = Color.BLACK;
        List<Long> actualSizeIds = sizeIds;
        int topPriceRange = maxPrice;

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds = filteredProduct.getSizes().stream()
                                                            .map(SizeDto::getSizeId)
                                                            .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.stream()
                                .anyMatch(sizeId -> actualSizeIds.contains(sizeId)));
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutMaxPrice() {
        //prepare
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                .categories(categoryIds)
                                                .colors(colors)
                                                .sizes(sizeIds)
                                                .minPrice(minPrice)
                                                .build();

        Long expectedCategoryId = 3L;
        Color expectedColor = Color.BLACK;
        List<Long> actualSizeIds = sizeIds;
        int bottomPriceRange = minPrice;

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds = filteredProduct.getSizes().stream()
                                                            .map(SizeDto::getSizeId)
                                                            .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertEquals(expectedColor, colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.stream()
                                .anyMatch(sizeId -> actualSizeIds.contains(sizeId)));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutCategoriesAndColorsAndMaxPrice() {
        //prepare
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                .sizes(sizeIds)
                                                .minPrice(minPrice)
                                                .build();

        List<Long> actualSizeIds = sizeIds;
        int bottomPriceRange = minPrice;

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<Long> filteredProductSizeIds = filteredProduct.getSizes().stream()
                                                            .map(SizeDto::getSizeId)
                                                            .collect(Collectors.toUnmodifiableList());
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertNotNull(categoryIdOfFilteredProduct);
            assertNotNull(colorOfFilteredProduct);
            assertTrue(filteredProductSizeIds.stream()
                                .anyMatch(sizeId -> actualSizeIds.contains(sizeId)));
            assertTrue(bottomPriceRange <= priceOfFilteredProduct);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutColorsAndSizesAndMinPrice() {
        //prepare
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                .categories(categoryIds)
                                                .maxPrice(maxPrice)
                                                .build();

        Long expectedCategoryId = 3L;
        int topPriceRange = maxPrice;

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<SizeDto> filteredProductSizes = filteredProduct.getSizes();
            int priceOfFilteredProduct = filteredProduct.getPrice().intValue();

            assertEquals(expectedCategoryId, categoryIdOfFilteredProduct);
            assertNotNull(colorOfFilteredProduct);
            assertFalse(filteredProductSizes.isEmpty());
            assertTrue(priceOfFilteredProduct <= topPriceRange);
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllBySearchingQuery() {
        //prepare
        String query = "кур";
        ProductFilterDto productFilter = ProductFilterDto.builder()
                                                        .query(query)
                                                        .build();

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            String productName = filteredProduct.getName().toLowerCase();
            assertTrue(productName.contains(query));
        }
    }

    @Test
    void shouldReturnAllProductMatchesWhenFindAllByFiltersWithoutAllFilters() {
        //prepare
        ProductFilterDto productFilter = ProductFilterDto.builder().build();

        //when
        List<ProductCategorySizesDto> filteredProductsDto = productService.findAllBy(productFilter);

        //then
        for (ProductCategorySizesDto filteredProduct : filteredProductsDto) {
            Long categoryIdOfFilteredProduct = filteredProduct.getCategory().getCategoryId();
            Color colorOfFilteredProduct = filteredProduct.getColor();
            List<SizeDto> filteredProductSizes = filteredProduct.getSizes();
            BigDecimal priceOfFilteredProduct = filteredProduct.getPrice();

            assertNotNull(categoryIdOfFilteredProduct);
            assertNotNull(colorOfFilteredProduct);
            assertFalse(filteredProductSizes.isEmpty());
            assertNotNull(priceOfFilteredProduct);
        }
    }
}