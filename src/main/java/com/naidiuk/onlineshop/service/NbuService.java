package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.NbuRateDto;

import java.util.Map;

public interface NbuService {
    Map<String, NbuRateDto> getRates();
}
