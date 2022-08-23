package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.error.NbuRateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CurrencyConverterServiceImpl implements CurrencyConverterService {
    private final NbuService nbuService;

    @Override
    public BigDecimal convertTo(Currency currency, BigDecimal price) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency is null.");
        }
        List<NbuRateDto> nbuRates = nbuService.getRates();
        if (nbuRates.isEmpty()) {
            throw new NoSuchElementException("List of nbu rates is empty.");
        }
        for (NbuRateDto nbuRate : nbuRates) {
            if ((nbuRate.getCurrencyCode()).equals(currency.getCurrencyCode())) {
                return price.divide(nbuRate.getCurrencyRate(), 2, RoundingMode.HALF_UP);
            }
        }
        String message = String.format("Nbu rate with currency=%s not found", currency.getCurrencyCode());
        throw new NbuRateNotFoundException(message);
    }
}
