package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.dto.CompanyProductsDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> findAll();
    CompanyProductsDto findById(Long companyId);
}
