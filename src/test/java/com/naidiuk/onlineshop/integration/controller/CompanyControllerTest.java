package com.naidiuk.onlineshop.integration.controller;

import com.naidiuk.onlineshop.controller.CompanyController;
import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CompanyService companyService;

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
}