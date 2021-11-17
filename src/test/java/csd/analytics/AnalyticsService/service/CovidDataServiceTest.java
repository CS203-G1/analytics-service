package csd.analytics.AnalyticsService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import csd.analytics.exception.CovidDataNotFoundException;
import csd.analytics.model.CovidData;
import csd.analytics.repository.CovidDataRepository;
import csd.analytics.service.CovidDataServiceImpl;
import csd.analytics.util.ClockUtil;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CovidDataServiceTest {
    @Mock
    CovidDataRepository covidDataRepository;

    @Mock
    ClockUtil clockUtil;

    @InjectMocks
    CovidDataServiceImpl covidDataServiceImpl;

    private final static LocalDate LOCAL_DATE = LocalDate.of(2021, 01, 01);
    private Clock fixedClock;

    @BeforeEach
    public void setup() {
        fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        when(clockUtil.getClock()).thenReturn(fixedClock);
    }

    @Test
    public void getAllCovidDataByOneMonth_CovidDataExist_ReturnListOfCovidData() {
        CovidData covidData = new CovidData(UUID.randomUUID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
        LocalDateTime end = LocalDateTime.now(clockUtil.getClock());
        LocalDateTime start = end.minusMonths(1);

        when(covidDataRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(start, end))
                .thenReturn(new ArrayList<CovidData>() {
                    {
                        add(covidData);
                    }
                });

        List<CovidData> covidDatas = covidDataServiceImpl.getAllCovidDataByOneMonth();

        assertNotNull(covidDatas);
        assertEquals(1, covidDatas.size());
        verify(covidDataRepository, times(1)).findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(start, end);
    }

    @Test
    public void getAllCovidDataByOneMonth_CovidDataNotExist_ThrowException() {
        LocalDateTime end = LocalDateTime.now(clockUtil.getClock());
        LocalDateTime start = end.minusMonths(1);

        when(covidDataRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(start, end))
                .thenReturn(new ArrayList<CovidData>());

        String expectedExceptionMessage = "Covid data not found";
        Exception exception = assertThrows(CovidDataNotFoundException.class,
                () -> covidDataServiceImpl.getAllCovidDataByOneMonth());

        assertEquals(expectedExceptionMessage, exception.getMessage());
        verify(covidDataRepository, times(1)).findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(start, end);
    }
}