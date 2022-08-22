package com.naidiuk.onlineshop.service;

import java.math.BigDecimal;
import java.util.Currency;

public interface CurrencyConverterService {
    BigDecimal convertTo(Currency currency, BigDecimal productPrice);
}
