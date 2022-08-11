package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Test
    void testFindAllWithoutProducts() {
        //when
        List<CompanyDto> companiesDto = companyService.findAll();
        CompanyDto companyDto = companiesDto.get(2);

        //then
        assertNotNull(companyDto);
        assertNull(companyDto.getProducts());
    }

    @Test
    void testFindByIdWithProducts() {
        //prepare
        Long companyId = 1L;

        //when
        CompanyDto companyDto = companyService.findById(companyId);
        List<ProductDto> companyProductsDto = companyDto.getProducts();

        //then
        assertNotNull(companyDto);
        assertEquals(companyId, companyDto.getCompanyId());
        assertFalse(companyProductsDto.isEmpty());
    }

    @Test
    void testFindByWrongIdWithProducts() {
        //prepare
        Long wrongCompanyId = 11L;

        //then
        assertThrows(CompanyNotFoundException.class, () -> companyService.findById(wrongCompanyId));
    }
}