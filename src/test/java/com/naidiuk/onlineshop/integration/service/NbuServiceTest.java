package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.service.NbuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NbuServiceTest {
    @Autowired
    private NbuService nbuService;

    @Test
    void shouldReturnNbuQuotesWhenGetQuotes() {
        //when
        List<NbuRateDto> nbuRatesDto = nbuService.getRates();
        NbuRateDto nbuRateDto = nbuRatesDto.get(0);

        //then
        assertNotNull(nbuRateDto.getCurrencyLocalName());
        assertNotNull(nbuRateDto.getCurrencyRate());
        assertNotNull(nbuRateDto.getCurrencyCode());
        assertNotNull(nbuRateDto.getExchangeDate());
    }
}