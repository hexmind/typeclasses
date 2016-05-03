package pl.hexmind.tc.evaluation;

import javaslang.control.Match;
import org.springframework.stereotype.Component;
import pl.hexmind.tc.order.Product;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

@Component
public class ProductEvaluation implements Evaluable<Product> {

    @Override
    public BigDecimal evaluate(Product product) {
        return Match.of(product)
                .whenType(Product.Basic.class).then(Product.Basic::getPrice)
                .whenType(Product.Discounted.class).then(d -> maxDiscount(d.getDiscount(), d.getDiscounted()))
                .whenType(Product.OutOfStock.class).then(ZERO)
                .get();
    }

    private BigDecimal maxDiscount(Double maxDiscount, Product product) {
        return Match.of(product)
                .whenType(Product.Discounted.class).then(d -> maxDiscount(maxDiscount, d))
                .whenTypeIn(
                        Product.Basic.class,
                        Product.OutOfStock.class
                ).then(p -> calculatePrice(maxDiscount, evaluate(p)))
                .get();
    }

    private BigDecimal maxDiscount(Double maxDiscount, Product.Discounted discounted) {
        double max = Math.max(maxDiscount, discounted.getDiscount());
        return maxDiscount(max, discounted.getDiscounted());
    }

    private BigDecimal calculatePrice(Double discount, BigDecimal basePrice) {
        return basePrice.multiply(ONE.subtract(BigDecimal.valueOf(discount)));
    }
};