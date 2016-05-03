package pl.hexmind.tc

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
abstract class SpringSpecification extends Specification {

    @Configuration
    @ComponentScan('pl.hexmind.tc')
    public static class SpringConfig {
    }
}
