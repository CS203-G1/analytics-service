package csd.analytics.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.analytics.model.Statistic;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistic, UUID> {

}