package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CompanyProductsDto {
    private Long companyId;
    private String name;
    @Builder.Default
    private List<ProductDto> products = new ArrayList<>();
}
