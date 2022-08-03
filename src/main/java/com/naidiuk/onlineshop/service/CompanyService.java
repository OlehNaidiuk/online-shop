package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> findAll();
}
