package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.error.NbuException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NbuServiceImpl implements NbuService {
    @Value("${nbu.rates-url}")
    private String nbuRatesUrl;
    private final RestTemplate restTemplate;

    @Override
    public List<NbuRateDto> getRates() {
        try {
            ResponseEntity<NbuRateDto[]> nbuResponse = restTemplate.getForEntity(nbuRatesUrl, NbuRateDto[].class);
            if (nbuResponse.getBody() != null) {
                return List.of(nbuResponse.getBody());
            }
            return Collections.emptyList();
        } catch (Exception e) {
            throw new NbuException("NBU error.", e);
        }
    }
}
