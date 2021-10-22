package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import csd.analytics.model.Company;

public interface CompanyService {
    List<UUID> getAllCompanyIds();
    List<Company> getAllCompany();
}
