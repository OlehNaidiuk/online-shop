package com.naidiuk.onlineshop.repository;

import com.naidiuk.onlineshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c FROM Category c  LEFT JOIN FETCH c.products WHERE c.categoryId=:categoryId")
    Optional<Category> findByIdWithProducts(Long categoryId);
}
