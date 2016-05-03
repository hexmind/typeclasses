package pl.hexmind.tc.evaluation
import org.springframework.beans.factory.annotation.Autowired
import pl.hexmind.tc.SpringSpecification
import pl.hexmind.tc.order.OrderFixture
import pl.hexmind.tc.order.Product
import pl.hexmind.tc.order.ProductFixture
import spock.lang.Unroll

@Unroll
class ProductEvaluationSpec extends SpringSpecification implements OrderFixture, ProductFixture {

    @Autowired
    Evaluable<Product> productEvaluation

    def "should evaluate #product price"() {
        expect:
            productEvaluation.evaluate(product) == price as BigDecimal

        where:
            product                                    | price
            productFor100                              | 100
            new Product.OutOfStock(productFor100)      | 0
            new Product.Discounted(productFor100, 0.1) | 90
            new Product.Discounted(productFor100, 1.0) | 0
            new Product.Discounted(productFor100, 0.0) | 100
    }

    def "should product costs #finalPrice when it has discounts #firstDiscount and #secondDiscount"() {
        given:
            Product first = new Product.Discounted(productFor100, firstDiscount)
            Product both = new Product.Discounted(first, secondDiscount)
        expect:
            productEvaluation.evaluate(both) == finalPrice
        where:
            firstDiscount | secondDiscount || finalDiscount
            0.1           | 0.1            || 0.1
            0.2           | 0.1            || 0.2
            0.1           | 0.2            || 0.2
            finalPrice = 100 * (1 - finalDiscount)
    }
}