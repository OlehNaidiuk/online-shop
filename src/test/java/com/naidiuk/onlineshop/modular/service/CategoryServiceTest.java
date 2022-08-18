package com.naidiuk.onlineshop.modular.service;

import com.naidiuk.onlineshop.dto.CategoryDto;
import com.naidiuk.onlineshop.dto.CategoryProductsDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.dto.SizeDto;
import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.Size;
import com.naidiuk.onlineshop.error.CategoryNotFoundException;
import com.naidiuk.onlineshop.repository.CategoryRepository;
import com.naidiuk.onlineshop.service.CategoryService;
import com.naidiuk.onlineshop.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository mockedCategoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(mockedCategoryRepository);
    }

    @Test
    void findAll() {
        //prepare
        Category first = mock(Category.class);
        List<Category> categories = new ArrayList<>();
        categories.add(first);

        doReturn(categories).when(mockedCategoryRepository).findAll();

        //when
        List<CategoryDto> categoriesDto = categoryService.findAll();

        //then
        assertNotNull(categoriesDto);
        assertEquals(1, categoriesDto.size());
        verify(mockedCategoryRepository, times(1)).findAll();
    }

    @Test
    void shouldGetCategoryWithSizesAndProductsWhenFindByCategoryId() {
        //prepare
        Long categoryId = 7L;
        Size size = Size.builder().build();
        Product product = Product.builder().build();

        List<Size> sizes = new ArrayList<>();
        sizes.add(size);
        List<Product> products = new ArrayList<>();
        products.add(product);

        Category category = Category.builder()
                                    .categoryId(categoryId)
                                    .sizes(sizes)
                                    .products(products)
                                    .build();

        Optional<Category> categoryOptional = Optional.of(category);

        doReturn(categoryOptional).when(mockedCategoryRepository).findById(categoryId);

        //when
        CategoryProductsDto categoryProductsDto = categoryService.findById(categoryId);
        List<SizeDto> sizesDto = categoryProductsDto.getSizes();
        List<ProductDto> productsDto = categoryProductsDto.getProducts();

        //then
        assertNotNull(categoryProductsDto);
        assertFalse(sizesDto.isEmpty());
        assertFalse(productsDto.isEmpty());
    }

    @Test
    void shouldThrowsCategoryNotFoundExceptionWhenFindByWrongCategoryId() {
        //prepare
        Long wrongCategoryId = 12L;

        //then
        assertThrows(CategoryNotFoundException.class, () -> categoryService.findById(wrongCategoryId));
    }
}