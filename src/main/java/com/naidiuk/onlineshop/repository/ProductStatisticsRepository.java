package com.naidiuk.onlineshop.repository;

import com.naidiuk.onlineshop.entity.ProductStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<ProductStatistics, Long> {
}
