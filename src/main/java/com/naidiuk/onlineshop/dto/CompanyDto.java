package com.naidiuk.onlineshop.dto;

import com.naidiuk.onlineshop.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanyDto {
    private Long companyId;
    private String name;
    private List<Product> products;
}
