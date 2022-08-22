package com.naidiuk.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class NbuQuoteDto {
    @JsonProperty(value = "r030")
    private int currencyNumericCode;
    @JsonProperty(value = "txt")
    private String currencyLocalName;
    @JsonProperty(value = "rate")
    private BigDecimal currencyRate;
    @JsonProperty(value = "cc")
    private String currencyCode;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonProperty(value = "exchangedate")
    private LocalDate exchangeDate;
}
