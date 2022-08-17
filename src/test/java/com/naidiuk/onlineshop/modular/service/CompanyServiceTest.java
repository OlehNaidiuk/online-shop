package com.naidiuk.onlineshop.modular.service;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.dto.CompanyProductsDto;
import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.repository.CompanyRepository;
import com.naidiuk.onlineshop.service.CompanyService;
import com.naidiuk.onlineshop.service.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @Mock
    private CompanyRepository mockedCompanyRepository;

    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        companyService = new CompanyServiceImpl(mockedCompanyRepository);
    }

    @Test
    void testFindAllWithoutProducts() {
        //prepare
        Company first = mock(Company.class);
        List<Company> companies = new ArrayList<>();
        companies.add(first);

        doReturn(companies).when(mockedCompanyRepository).findAll();

        //when
        List<CompanyDto> companiesDto = companyService.findAll();

        //then
        assertFalse(companiesDto.isEmpty());
        assertEquals(1, companiesDto.size());
        verify(mockedCompanyRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdWithProducts() {
        //prepare
        Long companyId = 1L;
        Company company = Company.builder()
                                .companyId(companyId)
                                .build();
        Optional<Company> companyOptional = Optional.of(company);

        doReturn(companyOptional).when(mockedCompanyRepository).findById(companyId);

        //when
        CompanyProductsDto companyProductsDto = companyService.findById(companyId);

        //then
        assertNotNull(companyProductsDto);
        assertEquals(1L, companyProductsDto.getCompanyId());
        assertNotNull(companyProductsDto.getProducts());
    }

    @Test
    void testFindByWrongIdWithProducts() {
        //prepare
        Long wrongCompanyId = 11L;

        //then
        assertThrows(CompanyNotFoundException.class, () -> companyService.findById(wrongCompanyId));
    }
}