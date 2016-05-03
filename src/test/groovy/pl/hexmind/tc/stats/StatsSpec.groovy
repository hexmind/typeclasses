package pl.hexmind.tc.stats
import org.springframework.beans.factory.annotation.Autowired
import pl.hexmind.tc.SpringSpecification
import pl.hexmind.tc.order.OrderFixture
import pl.hexmind.tc.order.ProductFixture
import spock.lang.Unroll

@Unroll
class StatsSpec extends SpringSpecification implements OrderFixture, ProductFixture {

    @Autowired
    Stats<Long> longStats

    def "should calculate average from #ints"() {
        given:
            List<Long> numbers = ints.collect(Long.&valueOf)
        and:
            long size = numbers.size()
            long average = size == 0L ? 0 : numbers.sum() / size
        expect:
            longStats.mean(numbers) == average
        where:
            ints         | _
            []           | _
            [2]          | _
            [1, 2]       | _
            [2, 2]       | _
            [10, 20, 30] | _
            [10, 20, 30] | _
    }
}
