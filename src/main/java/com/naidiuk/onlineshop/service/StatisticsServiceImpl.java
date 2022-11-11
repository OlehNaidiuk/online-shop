package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.ProductStatisticsDto;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.ProductStatistics;
import com.naidiuk.onlineshop.mapper.StatisticsMapper;
import com.naidiuk.onlineshop.repository.ProductStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final ProductStatisticsRepository productStatisticsRepository;

    @Override
    public void incrementProductView(Product product) {
        ProductStatistics productStatistics = productStatisticsRepository.findById(product.getProductId())
                                                                    .orElse(new ProductStatistics());
        long productViews = productStatistics.getProductViews();
        productStatistics.setProductViews(productViews + 1);
        product.addProductStatistics(productStatistics);
        productStatisticsRepository.save(productStatistics);
    }

    @Override
    public List<ProductStatisticsDto> getSortedProductsStatistics(String sortDirection) {
        Sort sortByProductViews;
        if (sortDirection.equals("asc")) {
            sortByProductViews = Sort.by(Sort.Direction.ASC, "productViews");
        } else {
            sortByProductViews = Sort.by(Sort.Direction.DESC, "productViews");
        }
        List<ProductStatistics> sortedProductStatistics = productStatisticsRepository.findAll(sortByProductViews);
        return sortedProductStatistics.stream()
                .map(StatisticsMapper::transformToDto)
                .collect(Collectors.toList());
    }
}
