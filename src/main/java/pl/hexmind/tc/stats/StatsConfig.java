package pl.hexmind.tc.stats;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class StatsConfig {

    @Bean
    Stats<BigDecimal> bigDecimalStats() {
        return Stats.<BigDecimal>builder()
                .valueOf(BigDecimal::valueOf)
                .zero(BigDecimal.ZERO)
                .add(BigDecimal::add)
                .divide(BigDecimal::divide)
                .build();
    }

    @Bean
    Stats<Long> longStats() {
        return Stats.<Long>builder()
                .valueOf(Long::valueOf)
                .zero(0L)
                .add((m, n) -> m + n)
                .divide((m, n) -> m / n)
                .build();
    }
}
