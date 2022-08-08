package com.naidiuk.onlineshop.modular.controller;

import com.naidiuk.onlineshop.controller.CompanyController;
import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {
    @Mock
    private CompanyService mockedCompanyService;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private CompanyController companyController;

    @Test
    void testFindAll() {
        //prepare
        List<CompanyDto> companiesDto = new ArrayList<>();
        doReturn(companiesDto).when(mockedCompanyService).findAll();

        //when
        ResponseEntity<List<CompanyDto>> responseEntity = companyController.findAll(request);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(companiesDto, responseEntity.getBody());
        verify(mockedCompanyService, times(1)).findAll();
    }

    @Test
    void testFindAllProductsByCompanyId() {
        //prepare
        Long companyId = 1L;
        CompanyDto companyDto = CompanyDto.builder().companyId(companyId).build();

        doReturn(companyDto).when(mockedCompanyService).findById(companyId);

        //when
        CompanyDto foundCompanyDto = companyController.findAllProductsByCompanyId(companyId, request);

        //then
        assertNotNull(foundCompanyDto);
    }

    @Test
    void testFindAllProductsByWrongCompanyId() {
        //prepare
        Long wrongCompanyId = 11L;

        doThrow(new CompanyNotFoundException("Company with id=" + wrongCompanyId + " not found."))
                .when(mockedCompanyService)
                .findById(wrongCompanyId);

        //then
        assertThrows(CompanyNotFoundException.class, () -> companyController.findAllProductsByCompanyId(wrongCompanyId, request));
    }
}