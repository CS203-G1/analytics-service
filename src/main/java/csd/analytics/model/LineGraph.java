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
public class LineGraph {
    private int totalDeaths;
    private int totalRecovered;
    private int totalCases;
    private int newImportedCases;
    private int newLocalCases;
    private int newDormitoryCases;
    private LocalDateTime createdAt;
}
