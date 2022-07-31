package com.naidiuk.onlineshop.repository;

import com.naidiuk.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
