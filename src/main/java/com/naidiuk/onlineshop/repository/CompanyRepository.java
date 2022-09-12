package com.naidiuk.onlineshop.repository;

import com.naidiuk.onlineshop.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "SELECT c FROM Company c LEFT JOIN FETCH c.products WHERE c.companyId=:companyId")
    Optional<Company> findByIdWithProducts(Long companyId);
}
