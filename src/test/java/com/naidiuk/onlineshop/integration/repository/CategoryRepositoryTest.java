package com.naidiuk.onlineshop.integration.repository;

import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.Size;
import com.naidiuk.onlineshop.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testFindAll() {
        //when
        List<Category> categories = categoryRepository.findAll();

        //then
        assertFalse(categories.isEmpty());
    }

    @Test
    void shouldGetCategoryWithSizesAndProductsWhenFindByCategoryId() {
        //prepare
        Long categoryId = 7L;

        //when
        Category category = categoryRepository.findById(categoryId).get();
        List<Size> sizes = category.getSizes();
        List<Product> products = category.getProducts();

        //then
        assertNotNull(category);
        assertFalse(sizes.isEmpty());
        assertFalse(products.isEmpty());
    }

    @Test
    void shouldGetEmptyCategoryOptionalWhenFindByWrongCategoryId() {
        //prepare
        Long wrongCategoryId = 12L;

        //when
        Optional<Category> categoryOptional = categoryRepository.findById(wrongCategoryId);

        //then
        assertTrue(categoryOptional.isEmpty());
    }
}