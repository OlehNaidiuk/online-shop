package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.NbuQuoteDto;
import com.naidiuk.onlineshop.service.NbuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NbuServiceTest {
    @Autowired
    private NbuService nbuService;

    @Test
    void shouldReturnNbuQuotesWhenGetQuotes() {
        //when
        List<NbuQuoteDto> nbuQuotesDto = nbuService.getQuotes();
        NbuQuoteDto nbuQuoteDto = nbuQuotesDto.get(0);

        //then
        assertNotEquals(0, nbuQuoteDto.getCurrencyNumericCode());
        assertNotNull(nbuQuoteDto.getCurrencyLocalName());
        assertNotNull(nbuQuoteDto.getCurrencyRate());
        assertNotNull(nbuQuoteDto.getCurrencyCode());
        assertNotNull(nbuQuoteDto.getExchangeDate());
    }
}