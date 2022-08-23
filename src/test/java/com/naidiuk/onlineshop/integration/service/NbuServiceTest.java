package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.service.NbuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NbuServiceTest {
    @Autowired
    private NbuService nbuService;

    @Test
    void shouldReturnNbuQuotesWhenGetQuotes() {
        //when
        Map<String, NbuRateDto> nbuRates = nbuService.getRates();
        NbuRateDto usdNbuRate = nbuRates.get("USD");

        //then
        assertEquals("USD", usdNbuRate.getCurrencyCode());
        assertEquals("Долар США", usdNbuRate.getCurrencyLocalName());
        assertNotNull(usdNbuRate.getCurrencyRate());
        assertNotNull(usdNbuRate.getExchangeDate());
    }
}