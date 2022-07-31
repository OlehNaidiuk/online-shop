package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.entity.Company;

public class CompanyMapper {
    public static CompanyDto transformToDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyId(company.getCompanyId());
        companyDto.setName(company.getName());
        return companyDto;
    }

    public static Company transformToDao(CompanyDto companyDto) {
        Company company = new Company();
        company.setCompanyId(companyDto.getCompanyId());
        company.setName(companyDto.getName());
        return company;
    }
}
