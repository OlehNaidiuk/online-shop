package com.naidiuk.onlineshop.repository;

import com.naidiuk.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>,
                                            JpaSpecificationExecutor<Product> {

    @Query(value = "SELECT * FROM product ORDER BY random() LIMIT 10", nativeQuery = true)
    List<Product> findTenRandom();
    @Query(value = "SELECT p FROM Product p LEFT JOIN FETCH p.reviews WHERE p.productId=:productId")
    Optional<Product> findByIdWithReviews(Long productId);
}
