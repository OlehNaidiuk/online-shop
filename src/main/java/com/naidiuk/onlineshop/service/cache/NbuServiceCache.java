package com.naidiuk.onlineshop.service.cache;

import com.naidiuk.onlineshop.dto.NbuRateDto;

import java.util.Map;

public interface NbuServiceCache {
    Map<String, NbuRateDto> getRates();
}
