package com.naidiuk.onlineshop.cache;

import com.naidiuk.onlineshop.entity.Company;

import java.util.List;

public interface CompanyCache {
    Company get(Long key);
    List<Company> getAll();
}
