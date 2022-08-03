package com.naidiuk.onlineshop.repository;

import com.naidiuk.onlineshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
