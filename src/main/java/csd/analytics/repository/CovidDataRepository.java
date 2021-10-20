package csd.analytics.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.analytics.model.CovidData;

@Repository
public interface CovidDataRepository extends JpaRepository<CovidData, UUID> {
    List<CovidData> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDateTime start, LocalDateTime end);
}
