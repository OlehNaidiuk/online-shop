package com.naidiuk.onlineshop.service;

import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testFindAll() {
        //when
        List<Company> companies = companyRepository.findAll();
        int expectedSize = 6;

        //then
        assertFalse(companies.isEmpty());
        assertEquals(expectedSize, companies.size());
    }
}