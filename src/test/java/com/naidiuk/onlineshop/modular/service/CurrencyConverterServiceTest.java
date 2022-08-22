package com.naidiuk.onlineshop.modular.service;

import com.naidiuk.onlineshop.dto.NbuQuoteDto;
import com.naidiuk.onlineshop.error.NbuQuoteNotFoundException;
import com.naidiuk.onlineshop.service.CurrencyConverterService;
import com.naidiuk.onlineshop.service.CurrencyConverterServiceImpl;
import com.naidiuk.onlineshop.service.NbuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CurrencyConverterServiceTest {
    @Mock
    private NbuService mockedNbuService;
    private CurrencyConverterService currencyConverterService;

    @BeforeEach
    void setUp() {
        currencyConverterService = new CurrencyConverterServiceImpl(mockedNbuService);
    }

    @Test
    void shouldReturnPriceByCurrencyWhenConvertByCurrencyRate() {
        //prepare
        NbuQuoteDto usdNbuQuote = NbuQuoteDto.builder()
                                            .currencyCode("USD")
                                            .currencyRate(BigDecimal.valueOf(36.5686))
                                            .build();
        NbuQuoteDto eurNbuQuote = NbuQuoteDto.builder()
                                            .currencyCode("EUR")
                                            .currencyRate(BigDecimal.valueOf(36.9787))
                                            .build();

        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        BigDecimal price = BigDecimal.valueOf(1999.79);

        List<NbuQuoteDto> nbuQuotes = List.of(usdNbuQuote, eurNbuQuote);

        doReturn(nbuQuotes).when(mockedNbuService).getQuotes();

        //when
        BigDecimal priceByUsd = currencyConverterService.convertTo(usd, price);
        BigDecimal priceByEur = currencyConverterService.convertTo(eur, price);

        //then
        assertEquals(BigDecimal.valueOf(54.69), priceByUsd);
        assertEquals(BigDecimal.valueOf(54.08), priceByEur);
    }

    @Test
    void shouldThrowNbuQuoteNotFoundExceptionWhenConvertToWrongCurrency() {
        //prepare
        List<NbuQuoteDto> nbuQuotes = new ArrayList<>();
        Currency rub = Currency.getInstance("RUB");
        BigDecimal price = BigDecimal.valueOf(123.45);

        doReturn(nbuQuotes).when(mockedNbuService).getQuotes();

        //then
        assertThrows(NbuQuoteNotFoundException.class, () -> currencyConverterService.convertTo(rub, price));
    }
}