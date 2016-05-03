package pl.hexmind.tc.order

import org.springframework.beans.factory.annotation.Autowired
import pl.hexmind.tc.SpringSpecification
import spock.lang.Unroll

@Unroll
class OrdersSpec extends SpringSpecification implements OrderFixture, ProductFixture {

    @Autowired
    Orders orders

    def "should validate #order"() {
        expect:
            orders.isValid(order)
        where:
            order << [cost100only, cost100and200, cost100x2and200, canceled, cost100x2and200allDoubled, cost100x2and200andCanceled]

    }

    def "should calculate average #average from #someOrders"() {
        expect:
            orders.average(someOrders) == average as BigDecimal
        where:
            someOrders                              || average
            [cost100only]                           || 100
            [cost100and200]                         || 300
            [cost100only, cost100only, cost100only] || 100
            [cost100only, cost100and200]            || 200
            [cost100and200]                         || 300
            [cost100x2and200]                       || 400
            [cost100and200, cost100x2and200]        || 350
    }
}
