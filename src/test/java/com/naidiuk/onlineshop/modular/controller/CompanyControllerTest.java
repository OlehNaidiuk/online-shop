package com.naidiuk.onlineshop.modular.controller;

import com.naidiuk.onlineshop.controller.CompanyController;
import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {
    @Mock
    private CompanyService mockedCompanyService;
    @InjectMocks
    private CompanyController companyController;

    @Test
    void testFindAll() {
        //prepare
        List<CompanyDto> companiesDto = new ArrayList<>();
        doReturn(companiesDto).when(mockedCompanyService).findAll();

        //when
        ResponseEntity<List<CompanyDto>> responseEntity = companyController.findAll();

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(companiesDto, responseEntity.getBody());
        verify(mockedCompanyService, times(1)).findAll();
    }
}