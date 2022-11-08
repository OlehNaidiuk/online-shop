package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.StatisticsDto;
import com.naidiuk.onlineshop.entity.Product;

import java.util.List;

public interface StatisticsService {
    void incrementProductView(Product product);
    List<StatisticsDto> getProductsStatisticsSortedByAscending();
    List<StatisticsDto> getProductsStatisticsSortedByDescending();
}
