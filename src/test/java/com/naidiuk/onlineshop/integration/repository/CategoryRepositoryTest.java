package com.naidiuk.onlineshop.integration.repository;

import com.naidiuk.onlineshop.entity.Category;
import com.naidiuk.onlineshop.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
}