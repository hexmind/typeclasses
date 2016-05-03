package pl.hexmind.tc.evaluation;

import javaslang.control.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hexmind.tc.order.Order;
import pl.hexmind.tc.order.Product;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Component
public class OrderEvaluation implements Evaluable<Order> {

    private final Evaluable<Product> productEvaluable;

    @Autowired
    public OrderEvaluation(Evaluable<Product> productEvaluable) {
        this.productEvaluable = productEvaluable;
    }

    @Override
    public BigDecimal evaluate(Order order) {
        return Match.of(order)
                .whenType(Order.GeneralOrder.class).then(this::evaluateGeneral)
                .whenType(Order.ComplexOrder.class).then(this::evaluateComplex)
                .whenType(Order.CanceledOrder.class).then(ZERO)
                .get();
    }

    private BigDecimal evaluateGeneral(Order.GeneralOrder o) {
        return o.getProducts().stream()
                .map(productEvaluable::evaluate)
                .reduce(ZERO, BigDecimal::add);
    }

    private BigDecimal evaluateComplex(Order.ComplexOrder o) {
        return o.getOrders().stream()
                .map(this::evaluate)
                .reduce(ZERO, BigDecimal::add);
    }

};