package com.naidiuk.onlineshop.repository;

import com.naidiuk.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>,
                                            JpaSpecificationExecutor<Product> {

    @Query(value = "SELECT * FROM product ORDER BY random() LIMIT 10", nativeQuery = true)
    List<Product> findTenRandom();
}
