package csd.analytics.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.model.Company;
import csd.analytics.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<UUID> getAllCompanyIds() {
        List<Company> companies = companyRepository.findAll();
        List<UUID> companyIds = companies
                .stream()
                .map(company -> company.getId())
                .collect(Collectors.toList());

        return companyIds;
    }
}
