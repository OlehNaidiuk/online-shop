package com.naidiuk.onlineshop.modular.service;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.entity.Company;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    void testFindAll() {
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
}