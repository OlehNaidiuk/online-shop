package com.naidiuk.onlineshop.repository;

import com.naidiuk.onlineshop.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Override
    Optional<Company> findById(Long companyId);
}
