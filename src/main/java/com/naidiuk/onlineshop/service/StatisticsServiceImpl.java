package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.StatisticsDto;
import com.naidiuk.onlineshop.entity.Product;
import com.naidiuk.onlineshop.entity.Statistics;
import com.naidiuk.onlineshop.mapper.StatisticsMapper;
import com.naidiuk.onlineshop.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    @Override
    public void incrementProductView(Product product) {
        Optional<Statistics> statisticsOptional = statisticsRepository.findById(product.getProductId());
        Statistics statistics;
        if (statisticsOptional.isEmpty()) {
            statistics = new Statistics();
        } else {
            statistics = statisticsOptional.get();
        }
        long views = statistics.getViews();
        statistics.setViews(views + 1);
        product.setStatistics(statistics);
        statistics.setProduct(product);
        statisticsRepository.save(statistics);
    }

    @Override
    public List<StatisticsDto> getProductsStatisticsSortedByAscending() {
        List<Statistics> productStatistics = statisticsRepository.findAll(Sort.by(Sort.Direction.ASC, "views"));
        return productStatistics.stream()
                .map(StatisticsMapper::transformToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatisticsDto> getProductsStatisticsSortedByDescending() {
        List<Statistics> productStatistics = statisticsRepository.findAll(Sort.by(Sort.Direction.DESC, "views"));
        return productStatistics.stream()
                .map(StatisticsMapper::transformToDto)
                .collect(Collectors.toList());
    }
}
