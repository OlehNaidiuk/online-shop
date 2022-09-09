package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.*;
import com.naidiuk.onlineshop.entity.Color;
import com.naidiuk.onlineshop.entity.Male;
import com.naidiuk.onlineshop.error.ProductNotFoundException;
import com.naidiuk.onlineshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Currency;
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
            assertEquals(0, productFilter.getMinPrice());
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
            assertEquals(100_000, productFilter.getMaxPrice());
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
            assertEquals(100_000, productFilter.getMaxPrice());
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
            assertEquals(0, productFilter.getMinPrice());
            assertTrue(priceOfFilteredProduct <= topPriceRange);
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
            assertEquals(0, productFilter.getMinPrice());
            assertEquals(100_000, productFilter.getMaxPrice());
        }
    }

    @Test
    void shouldReturnSavedProductWithIdWhenSaveProduct() {
        //prepare
        CategoryDto categoryDto = CategoryDto.builder().categoryId(2L).build();
        CompanyDto companyDto = CompanyDto.builder().companyId(6L).build();
        List<SizeDto> sizesDto = List.of(SizeDto.builder().sizeId(2L).build(),
                                        SizeDto.builder().sizeId(3L).build(),
                                        SizeDto.builder().sizeId(4L).build(),
                                        SizeDto.builder().sizeId(5L).build());
        ProductAllDto productAllDto = ProductAllDto.builder()
                                                .price(BigDecimal.valueOf(4099.00))
                                                .currency(Currency.getInstance("UAH"))
                                                .color(Color.ORANGE)
                                                .name("Куртка вельветова Levi's")
                                                .description("Куртка із колекції Levi's." +
                                                        " Злегка утеплена модель виготовлена з вельветового матеріалу.")
                                                .male(Male.MEN)
                                                .category(categoryDto)
                                                .sizes(sizesDto)
                                                .company(companyDto)
                                                .build();

        //when
        ProductAllDto savedProductDto = productService.save(productAllDto);

        List<Long> expectedSizeIds = sizesDto.stream()
                                        .map(SizeDto::getSizeId)
                                        .collect(Collectors.toUnmodifiableList());
        List<Long> savedSizeIds = savedProductDto.getSizes().stream()
                                        .map(SizeDto::getSizeId)
                                        .collect(Collectors.toUnmodifiableList());

        //then
        assertNotNull(savedProductDto.getProductId());
        assertSame(productAllDto.getColor(), savedProductDto.getColor());
        assertEquals(productAllDto.getName(), savedProductDto.getName());
        assertEquals(categoryDto.getCategoryId(), savedProductDto.getCategory().getCategoryId());
        assertEquals(companyDto.getCompanyId(), savedProductDto.getCompany().getCompanyId());
        assertTrue(expectedSizeIds.containsAll(savedSizeIds));
    }

    @Test
    void shouldReturnUpdatedProductWhenUpdateProduct() {
        //prepare
        ProductDto productDtoToUpdate = ProductDto.builder()
                                                .productId(1L)
                                                .price(BigDecimal.valueOf(499.00))
                                                .currency(Currency.getInstance("UAH"))
                                                .color(Color.BLUE)
                                                .name("Сандалі HUGO Jens Sand")
                                                .description("Сандалі із колекції HUGO." +
                                                        " Модель виготовлена із текстильного матеріалу.")
                                                .male(Male.MEN)
                                                .build();

        //when
        ProductDto updatedProductDto = productService.update(productDtoToUpdate);

        //then
        assertEquals(productDtoToUpdate.getProductId(), updatedProductDto.getProductId());
        assertEquals(productDtoToUpdate.getPrice(), updatedProductDto.getPrice());
        assertSame(productDtoToUpdate.getCurrency(), updatedProductDto.getCurrency());
        assertSame(productDtoToUpdate.getColor(), updatedProductDto.getColor());
        assertEquals(productDtoToUpdate.getName(), updatedProductDto.getName());
        assertEquals(productDtoToUpdate.getDescription(), updatedProductDto.getDescription());
        assertSame(productDtoToUpdate.getMale(), updatedProductDto.getMale());
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenUpdateProductWithWrongId() {
        //prepare
        ProductDto productDtoToUpdateWithWrongId = ProductDto.builder().productId(111L).build();

        //then
        assertThrows(ProductNotFoundException.class, () -> productService.update(productDtoToUpdateWithWrongId));
    }

    @Test
    void shouldReturnDeletedProductWhenDeleteById() {
        //prepare
        Long productIdToDelete = 22L;

        //when
        ProductDto deletedProductDto = productService.deleteById(productIdToDelete);

        //then
        assertEquals(productIdToDelete, deletedProductDto.getProductId());
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenDeleteProductWithWrongId() {
        //prepare
        Long wrongProductIdToDelete = 111L;

        //then
        assertThrows(ProductNotFoundException.class, () -> productService.deleteById(wrongProductIdToDelete));
    }
}