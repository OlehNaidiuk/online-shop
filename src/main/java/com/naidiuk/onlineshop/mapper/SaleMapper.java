package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.SaleDto;
import com.naidiuk.onlineshop.entity.Sale;

public class SaleMapper {
    public static SaleDto transformToDto(Sale sale) {
        if (sale == null) {
            return SaleDto.builder().build();
        }
        return SaleDto.builder()
                .saleId(sale.getSaleId())
                .value(sale.getValue())
                .build();
    }
}
