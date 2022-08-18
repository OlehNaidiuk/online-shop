package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.dto.CompanyProductsDto;
import com.naidiuk.onlineshop.dto.ProductDto;
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
    void testFindAllWithoutProducts() {
        //when
        List<CompanyDto> companiesDto = companyService.findAll();

        //then
        assertNotNull(companiesDto);
    }

    @Test
    void testFindByIdWithProducts() {
        //prepare
        Long companyId = 1L;

        //when
        CompanyProductsDto companyProductsDto = companyService.findById(companyId);
        List<ProductDto> productsDto = companyProductsDto.getProducts();

        //then
        assertNotNull(companyProductsDto);
        assertEquals(companyId, companyProductsDto.getCompanyId());
        assertFalse(productsDto.isEmpty());
    }

    @Test
    void testFindByWrongIdWithProducts() {
        //prepare
        Long wrongCompanyId = 11L;

        //then
        assertThrows(CompanyNotFoundException.class, () -> companyService.findById(wrongCompanyId));
    }
}