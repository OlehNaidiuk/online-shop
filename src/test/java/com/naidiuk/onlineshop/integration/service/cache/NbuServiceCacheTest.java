package com.naidiuk.onlineshop.integration.service.cache;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.service.cache.NbuServiceCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NbuServiceCacheTest {
    @Autowired
    private NbuServiceCache nbuServiceCache;

    @Test
    void shouldReturnNbuRatesWhenGetRates() {
        //when
        Map<String, NbuRateDto> nbuRates = nbuServiceCache.getRates();
        NbuRateDto usdNbuRate = nbuRates.get("USD");

        //then
        assertEquals("USD", usdNbuRate.getCurrencyCode());
        assertEquals("Долар США", usdNbuRate.getCurrencyLocalName());
        assertNotNull(usdNbuRate.getCurrencyRate());
        assertNotNull(usdNbuRate.getExchangeDate());
    }
}