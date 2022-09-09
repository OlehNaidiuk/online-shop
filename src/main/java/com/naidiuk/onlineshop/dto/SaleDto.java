package com.naidiuk.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDto {
    private Long saleId;
    private BigDecimal value;
}
