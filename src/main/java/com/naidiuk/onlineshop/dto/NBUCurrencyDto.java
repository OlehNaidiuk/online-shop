package com.naidiuk.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class NBUCurrencyDto {
    private int r030;
    private String txt;
    private BigDecimal rate;
    private String cc;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate exchangedate;
}
