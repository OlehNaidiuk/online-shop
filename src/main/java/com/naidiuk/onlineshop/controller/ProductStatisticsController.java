package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.ProductStatisticsDto;
import com.naidiuk.onlineshop.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class ProductStatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findAllAndSort(
            @RequestParam(required = false, defaultValue = "desc") String sortDirection) {

        List<ProductStatisticsDto> sortedProductsStatisticsDto = statisticsService.getSortedProductsStatistics(sortDirection);
        return ResponseEntity.status(HttpStatus.OK).body(sortedProductsStatisticsDto);
    }
}
