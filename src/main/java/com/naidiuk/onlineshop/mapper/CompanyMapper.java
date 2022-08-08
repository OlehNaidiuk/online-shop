package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.entity.Company;

public class CompanyMapper {
    public static CompanyDto transformToDto(Company company) {
        return CompanyDto.builder()
                .companyId(company.getCompanyId())
                .name(company.getName())
                .products(company.getProducts())
                .build();
    }
}
