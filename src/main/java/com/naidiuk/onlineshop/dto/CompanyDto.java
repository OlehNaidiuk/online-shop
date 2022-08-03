package com.naidiuk.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyDto {
    private Long companyId;
    private String name;
}
