package com.naidiuk.onlineshop.modular.service;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.error.NbuRateNotFoundException;
import com.naidiuk.onlineshop.service.CurrencyConverterService;
import com.naidiuk.onlineshop.service.CurrencyConverterServiceImpl;
import com.naidiuk.onlineshop.service.NbuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

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
        NbuRateDto usdNbuRate = NbuRateDto.builder()
                                            .currencyCode("USD")
                                            .currencyRate(BigDecimal.valueOf(36.5686))
                                            .build();
        NbuRateDto eurNbuRate = NbuRateDto.builder()
                                            .currencyCode("EUR")
                                            .currencyRate(BigDecimal.valueOf(36.9787))
                                            .build();

        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        BigDecimal price = BigDecimal.valueOf(1999.79);

        Map<String, NbuRateDto> nbuRates = Map.of(usdNbuRate.getCurrencyCode(), usdNbuRate,
                                                eurNbuRate.getCurrencyCode(), eurNbuRate);

        doReturn(nbuRates).when(mockedNbuService).getRates();

        //when
        BigDecimal priceByUsd = currencyConverterService.convertTo(usd, price);
        BigDecimal priceByEur = currencyConverterService.convertTo(eur, price);

        //then
        assertEquals(BigDecimal.valueOf(54.69), priceByUsd);
        assertEquals(BigDecimal.valueOf(54.08), priceByEur);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCurrencyIsNull() {
        //prepare
        Currency nullableCurrency = null;
        BigDecimal price = BigDecimal.valueOf(123.45);

        //then
        assertThrows(IllegalArgumentException.class, () -> currencyConverterService.convertTo(nullableCurrency, price));
    }

    @Test
    void shouldThrowNbuQuoteNotFoundExceptionWhenConvertToWrongCurrency() {
        //prepare
        Map<String, NbuRateDto> nbuRates = Map.of("USD", NbuRateDto.builder().build());
        Currency rub = Currency.getInstance("RUB");
        BigDecimal price = BigDecimal.valueOf(123.45);

        doReturn(nbuRates).when(mockedNbuService).getRates();

        //then
        assertThrows(NbuRateNotFoundException.class, () -> currencyConverterService.convertTo(rub, price));
    }
}