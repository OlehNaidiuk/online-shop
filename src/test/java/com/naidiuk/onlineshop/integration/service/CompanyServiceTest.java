package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CompanyServiceTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testFindAll() {
        //when
        List<Company> companies = companyRepository.findAll();

        //then
        assertFalse(companies.isEmpty());
    }

    @Test
    void testFindById() {
        //prepare
        Long companyId = 1L;

        //when
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        //then
        assertTrue(companyOptional.isPresent());
    }

    @Test
    void testFindByWrongId() {
        //prepare
        Long wrongCompanyId = 11L;

        //when
        Optional<Company> companyOptional = companyRepository.findById(wrongCompanyId);

        //then
        assertFalse(companyOptional.isPresent());
    }
}