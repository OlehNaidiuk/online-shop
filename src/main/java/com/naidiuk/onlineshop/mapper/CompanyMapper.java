package com.naidiuk.onlineshop.mapper;

import com.naidiuk.onlineshop.dto.CompanyDto;
import com.naidiuk.onlineshop.dto.CompanyProductsDto;
import com.naidiuk.onlineshop.dto.ProductDto;
import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    public static CompanyDto transformToDto(Company company) {
        return CompanyDto.builder()
                .companyId(company.getCompanyId())
                .name(company.getName())
                .build();
    }

    public static CompanyProductsDto transformToDtoWithProducts(Company company) {
        List<Product> products = company.getProducts();
        List<ProductDto> productsDto = products.stream()
                                            .map(ProductMapper::transformToDto)
                                            .collect(Collectors.toList());;

        return CompanyProductsDto.builder()
                .companyId(company.getCompanyId())
                .name(company.getName())
                .products(productsDto)
                .build();
    }
}
