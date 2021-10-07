package csd.analytics.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.analytics.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    List<UUID> getAllCompanyIds();
}