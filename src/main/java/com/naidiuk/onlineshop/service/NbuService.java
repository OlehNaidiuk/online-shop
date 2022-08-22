package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.NbuQuoteDto;

import java.util.List;

public interface NbuService {
    List<NbuQuoteDto> getQuotes();
}
