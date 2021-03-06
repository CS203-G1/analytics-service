package csd.analytics.model;

import java.time.LocalDateTime;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

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
    private UUID id;

    @OneToMany(mappedBy = "employee", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EmployeeVaccination> employeeVaccinations;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumns({
            @JoinColumn(name="company_id", referencedColumnName="company_id"),
            @JoinColumn(name="department_id", referencedColumnName="id")
    })
    private Department department;

    @Column(name = "name")
    private String name;

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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_in_company")
    private Boolean isInCompany = true;
}
