package com.naidiuk.onlineshop.integration.controller;

import com.naidiuk.onlineshop.controller.CompanyController;
import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.dto.CompanyProductsDto;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private CompanyService companyService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testFindAll() throws Exception {
        //prepare
        CompanyDto firsDto = CompanyDto.builder().build();

        List<CompanyDto> companiesDto = new ArrayList<>();
        companiesDto.add(firsDto);

        doReturn(companiesDto).when(companyService).findAll();

        //then
        mockMvc.perform(get("/api/v1/companies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").value(hasSize(companiesDto.size())));
    }

    @Test
    void findAllProductsByCompanyId() throws Exception {
        //prepare
        CompanyProductsDto companyDto = CompanyProductsDto.builder()
                                        .companyId(1L)
                                        .build();

        doReturn(companyDto).when(companyService).findById(1L);

        //then
        mockMvc.perform(get("/api/v1/companies/{id}/products", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.products").value(companyDto.getProducts()));
    }

    @Test
    void findAllProductsByWrongCompanyId() throws Exception {
        //prepare
        doThrow(new CompanyNotFoundException("Company with id=11 not found."))
                .when(companyService).findById(11L);

        //then
        mockMvc.perform(get("/api/v1/companies/{id}/products", 11)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.statusCode").value(404))
                .andExpect(jsonPath("$.message").value("Company with id=11 not found."));
    }
}