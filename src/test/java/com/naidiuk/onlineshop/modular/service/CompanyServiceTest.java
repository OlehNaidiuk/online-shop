package com.naidiuk.onlineshop.modular.service;

import com.naidiuk.onlineshop.cache.CompanyCache;
import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.service.CompanyService;
import com.naidiuk.onlineshop.service.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @Mock
    private CompanyCache mockedCompanyCache;

    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        companyService = new CompanyServiceImpl(mockedCompanyCache);
    }

    @Test
    void testFindAll() {
        //prepare
        Company first = mock(Company.class);
        List<Company> companies = new ArrayList<>();
        companies.add(first);

        doReturn(companies).when(mockedCompanyCache).getAll();

        //when
        List<CompanyDto> companiesDto = companyService.findAll();

        //then
        assertFalse(companiesDto.isEmpty());
        assertEquals(1, companiesDto.size());
        verify(mockedCompanyCache, times(1)).getAll();
    }

    @Test
    void testFindById() {
        //prepare
        Long companyId = 1L;
        Company company = Company.builder()
                                .companyId(companyId)
                                .build();

        doReturn(company).when(mockedCompanyCache).get(companyId);

        //when
        CompanyDto companyDto = companyService.findById(companyId);

        //then
        assertNotNull(companyDto);
        assertEquals(1L, companyDto.getCompanyId());
    }

    @Test
    void testFindByWrongId() {
        //prepare
        Long wrongCompanyId = 11L;

        doThrow(new CompanyNotFoundException("Exception message")).when(mockedCompanyCache).get(wrongCompanyId);

        //then
        assertThrows(CompanyNotFoundException.class, () -> companyService.findById(wrongCompanyId));
    }
}