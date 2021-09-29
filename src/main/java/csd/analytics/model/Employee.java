package csd.analytics.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import csd.analytics.enumerator.HealthStatus;
import csd.analytics.enumerator.VaccinationBrand;
import csd.analytics.enumerator.VaccinationStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull(message = "ID must not be null")
    private UUID id;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeVaccination> employeeVaccinations;

    @NotNull(message = "VaccinationStatus must not be null")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vaccination_status")
    private VaccinationStatus vaccinationStatus;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vaccination_brand")
    private VaccinationBrand vaccinationBrand;

    @NotNull(message = "HealthStatus must not be null")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "health_status")
    private HealthStatus healthStatus;
}
