package csd.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.analytics.model.EmployeeVaccination;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeVaccinationRepository extends JpaRepository<EmployeeVaccination, UUID> {
    List<EmployeeVaccination> findByEmployeeId(UUID employeeId);
    Optional<EmployeeVaccination> findByIdAndEmployeeId(UUID employeeVaccinationId, UUID employeeId);
}
