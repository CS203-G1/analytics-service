package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return companyRepository.getAllCompanyIds();
    }
}
