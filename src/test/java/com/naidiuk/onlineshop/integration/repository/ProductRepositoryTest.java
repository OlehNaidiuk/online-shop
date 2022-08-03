package com.naidiuk.onlineshop.integration.repository;

import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindTenRandom() {
        //when
        List<Product> tenRandomProducts = productRepository.findTenRandom();

        //then
        assertFalse(tenRandomProducts.isEmpty());
        assertEquals(10, tenRandomProducts.size());
    }
}