package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Test
    void testFindAll() {
        //when
        List<CompanyDto> companiesDto = companyService.findAll();

        //then
        assertFalse(companiesDto.isEmpty());
    }

    @Test
    void testFindById() {
        //prepare
        Long companyId = 1L;

        //when
        CompanyDto companyDto = companyService.findById(companyId);

        //then
        assertNotNull(companyDto);
        assertEquals(companyId, companyDto.getCompanyId());
    }

    @Test
    void testFindByWrongId() {
        //prepare
        Long wrongCompanyId = 11L;

        //then
        assertThrows(CompanyNotFoundException.class, () -> companyService.findById(wrongCompanyId));
    }
}