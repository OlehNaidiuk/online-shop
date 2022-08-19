package com.naidiuk.onlineshop.error;

public class RequestedCurrencyNotFoundException extends RuntimeException {
    public RequestedCurrencyNotFoundException(String message) {
        super(message);
    }
}
