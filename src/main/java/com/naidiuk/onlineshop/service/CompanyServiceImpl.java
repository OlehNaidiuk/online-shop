package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.dto.CompanyProductsDto;
import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.mapper.CompanyMapper;
import com.naidiuk.onlineshop.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDto> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                        .map(CompanyMapper::transformToDto)
                        .collect(Collectors.toList());
    }

    @Override
    public CompanyProductsDto findById(Long companyId) {
        Company company = companyRepository.findByIdWithProducts(companyId)
                                    .orElseThrow(() ->
                                            new CompanyNotFoundException(
                                                    String.format("Company with id=%d not found.", companyId)));
        return CompanyMapper.transformToDtoWithProducts(company);
    }
}
