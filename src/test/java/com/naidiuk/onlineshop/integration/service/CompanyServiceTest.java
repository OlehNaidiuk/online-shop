package com.naidiuk.onlineshop.integration.service;

import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
}