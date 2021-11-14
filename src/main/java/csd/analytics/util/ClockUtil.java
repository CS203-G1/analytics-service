package csd.analytics.util;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClockUtil {
    @Bean
    public Clock getClock() {
        return Clock.systemDefaultZone();
    }
}
