package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> findAll() {
        List<CompanyDto> companiesDto = companyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(companiesDto);
    }
}
