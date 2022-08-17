package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class SaleDto {
    private Long saleId;
    private BigDecimal value;
}
