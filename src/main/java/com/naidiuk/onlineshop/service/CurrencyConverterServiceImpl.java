package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.NbuQuoteDto;
import com.naidiuk.onlineshop.error.NbuQuoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyConverterServiceImpl implements CurrencyConverterService {
    private final NbuService nbuService;

    @Override
    public BigDecimal convertTo(Currency currency, BigDecimal price) {
        List<NbuQuoteDto> nbuQuotes = nbuService.getQuotes();
        for (NbuQuoteDto nbuQuote : nbuQuotes) {
            if ((nbuQuote.getCurrencyCode()).equals(currency.getCurrencyCode())) {
                return price.divide(nbuQuote.getCurrencyRate(), 2, RoundingMode.HALF_UP);
            }
        }
        String message = String.format("Nbu quote with currency=%s not found", currency.getCurrencyCode());
        throw new NbuQuoteNotFoundException(message);
    }
}
