package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.SaleDto;
import com.naidiuk.onlineshop.entity.Sale;

public class SaleMapper {
    public static SaleDto transformToDto(Sale sale) {
        if (sale == null) {
            return null;
        }
        return SaleDto.builder()
                .saleId(sale.getSaleId())
                .value(sale.getValue())
                .build();
    }

    public static Sale transformToDao(SaleDto saleDto) {
        if (saleDto == null) {
            return null;
        }
        return Sale.builder()
                .saleId(saleDto.getSaleId())
                .value(saleDto.getValue())
                .build();
    }
}
