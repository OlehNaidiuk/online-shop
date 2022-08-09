package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.cache.CompanyCache;
import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyCache companyCache;

    @Override
    public List<CompanyDto> findAll() {
        List<Company> companies = companyCache.getAll();
        return companies.stream()
                .map(CompanyMapper::transformToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto findById(Long companyId) {
        Company company = companyCache.get(companyId);
        return CompanyMapper.transformToDto(company);
    }
}
