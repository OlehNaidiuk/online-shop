package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> findAll() {
        log.info("Request URL: {}, Host: {}, Address: {}, Request URI: {}, Request params: {}."
                , request.getRequestURL()
                , request.getRemoteHost()
                , request.getRemoteAddr()
                , request.getRequestURI()
                , request.getQueryString());
        List<CompanyDto> companiesDto = companyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(companiesDto);
    }
}
