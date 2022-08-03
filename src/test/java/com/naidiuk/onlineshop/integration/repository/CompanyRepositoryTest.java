package com.naidiuk.onlineshop.integration.repository;

import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyRepositoryTest {
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