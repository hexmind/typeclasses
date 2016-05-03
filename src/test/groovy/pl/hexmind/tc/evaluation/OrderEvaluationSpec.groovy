package pl.hexmind.tc.evaluation
import org.springframework.beans.factory.annotation.Autowired
import pl.hexmind.tc.SpringSpecification
import pl.hexmind.tc.order.Order
import pl.hexmind.tc.order.OrderFixture
import pl.hexmind.tc.order.ProductFixture
import spock.lang.Unroll

@Unroll
class OrderEvaluationSpec extends SpringSpecification implements OrderFixture, ProductFixture {

    @Autowired
    Evaluable<Order> orderEvaluation

    def "should evaluate #order as products prices sum #price"() {
        expect:
            orderEvaluation.evaluate(order) == price
        where:
            order                      || price
            cost100only                || 100.0
            cost100and200              || 300.0
            canceled                   || 0.0
            cost100x2and200            || 400.0
            cost100x2and200allDoubled  || 800.0
            cost100x2and200andCanceled || 400.0
    }
}