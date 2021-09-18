package csd.analytics.model;

import java.util.UUID;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

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

    @Column(name = "vaccination_status")
    private short vaccinationStatus;

    @Column(name = "vaccination_brand")
    private short vaccinationBrand;

    @Column(name = "health_status")
    private short healthStatus;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeVaccination> employeeVaccinations;
}
