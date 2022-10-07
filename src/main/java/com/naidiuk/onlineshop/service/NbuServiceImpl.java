package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.service.cache.NbuServiceCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NbuServiceImpl implements NbuService {

    private final NbuServiceCache nbuServiceCache;

    public NbuServiceImpl(@Qualifier("nbuServiceCacheFromXml")NbuServiceCache nbuServiceCache) {
        this.nbuServiceCache = nbuServiceCache;
    }

    @Override
    public Map<String, NbuRateDto> getRates() {
        return nbuServiceCache.getRates();
    }
}
