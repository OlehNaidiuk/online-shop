package com.naidiuk.onlineshop.cache;

import com.naidiuk.onlineshop.entity.Company;
import com.naidiuk.onlineshop.error.CompanyNotFoundException;
import com.naidiuk.onlineshop.repository.CompanyRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@EnableScheduling
@Component
public class CompanyCacheImpl implements CompanyCache {
    private final CompanyRepository companyRepository;
    private final Map<Long, Company> cache = new HashMap<>();

    public CompanyCacheImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        updateData();
    }

    @Override
    public Company get(Long key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            throw new CompanyNotFoundException("Company with id=" + key + " not found.");
        }
    }

    @Override
    public List<Company> getAll() {
        Collection<Company> companies = cache.values();
        return new ArrayList<>(companies);
    }

    @Scheduled(cron = "* * 4 * * ?")
    private void updateData() {
        cache.clear();
        List<Company> companies = companyRepository.findAll();
        for (Company company : companies) {
            cache.put(company.getCompanyId(), company);
        }
    }
}
