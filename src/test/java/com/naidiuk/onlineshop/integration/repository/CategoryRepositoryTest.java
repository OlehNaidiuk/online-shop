package com.naidiuk.onlineshop.integration.repository;

import com.naidiuk.onlineshop.entity.Catalog;
import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testFindAll() {
        //prepare
        Category actual = createCategory();
        categoryRepository.save(actual);
        //when
        List<Category> categories = categoryRepository.findAll();
        Category expected = categories.get(0);

        //then
        assertEquals(expected.getCategoryId(), actual.getCategoryId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCatalog(), actual.getCatalog());
    }

    private Category createCategory() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("кеди");
        category.setCatalog(Catalog.FOOTWEAR);
        return category;
    }
}