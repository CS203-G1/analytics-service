package csd.analytics.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BarGraph {
    private double swabRate;
    private double vaccinatedRate;
    private double fullyVaccinatedRate;
    private LocalDateTime createdAt;
}
