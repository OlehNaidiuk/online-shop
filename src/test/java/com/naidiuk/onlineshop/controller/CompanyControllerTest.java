package com.naidiuk.onlineshop.controller;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
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
        List<CompanyDto> companiesDto = createCompaniesDtoList();
        Mockito.when(companyService.findAll()).thenReturn(companiesDto);

        //then
        mockMvc.perform(get("/api/v1/companies").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").value(hasSize(6)));
    }

    private List<CompanyDto> createCompaniesDtoList() {
        CompanyDto first = new CompanyDto();
        first.setCompanyId(1L);
        first.setName("BOSS");

        CompanyDto second = new CompanyDto();
        second.setCompanyId(2L);
        second.setName("NIKE");

        CompanyDto third = new CompanyDto();
        third.setCompanyId(3L);
        third.setName("HUGO");

        CompanyDto fourth = new CompanyDto();
        fourth.setCompanyId(4L);
        fourth.setName("Reebok");

        CompanyDto fifth = new CompanyDto();
        fifth.setCompanyId(5L);
        fifth.setName("Emporio Armani");

        CompanyDto sixth = new CompanyDto();
        sixth.setCompanyId(6L);
        sixth.setName("Levi's");

        List<CompanyDto> companiesDto = new ArrayList<>(6);
        companiesDto.add(first);
        companiesDto.add(second);
        companiesDto.add(third);
        companiesDto.add(fourth);
        companiesDto.add(fifth);
        companiesDto.add(sixth);

        return companiesDto;
    }
}