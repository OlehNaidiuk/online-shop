package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.service.cache.NbuServiceCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NbuServiceImpl implements NbuService {
    private final NbuServiceCache nbuServiceCache;

    @Override
    public Map<String, NbuRateDto> getRates() {
        return nbuServiceCache.getRates();
    }
}
