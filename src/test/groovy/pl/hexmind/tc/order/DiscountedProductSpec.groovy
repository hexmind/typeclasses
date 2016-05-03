package pl.hexmind.tc.order

import org.springframework.beans.factory.annotation.Autowired
import pl.hexmind.tc.SpringSpecification
import pl.hexmind.tc.util.SimpleValidator
import spock.lang.Unroll

import javax.validation.ConstraintViolationException

@Unroll
class DiscountedProductSpec extends SpringSpecification {

    @Autowired
    SimpleValidator<Product> validator

    def "should create valid discounted product when #desc"() {
        given:
            Product basic = new Product.Basic(0, price)
            Product product = new Product.Discounted(basic, discount)
        when:
            validator.validate(product)
        then:
            noExceptionThrown()
        where:
            price | discount
            0.0   | 0.0
            100.0 | 0.0
            0.0   | 1.0
            100.0 | 1.0
            desc = [price: price, discount: discount]
    }

    def "shouldn't create invalid discounted product when #desc"() {
        given:
            Product basic = new Product.Basic(0, price)
            Product p = new Product.Discounted(basic, discount)
        when:
            validator.validate(p)
        then:
            thrown(ConstraintViolationException)
        where:
            price | discount
            -100  | 0.0
            100   | 2.1
            100   | -1.0
            desc = [price: price, discount: discount]
    }
}
