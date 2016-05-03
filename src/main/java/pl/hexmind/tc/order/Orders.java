package pl.hexmind.tc.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hexmind.tc.evaluation.Evaluable;
import pl.hexmind.tc.util.SimpleValidator;
import pl.hexmind.tc.stats.Stats;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Orders {

    private final Evaluable<Order> orderEvaluable;
    private final Stats<BigDecimal> stats;
    private final SimpleValidator<Order> orderValidator;

    @Autowired
    public Orders(Evaluable<Order> orderEvaluable, Stats<BigDecimal> stats, SimpleValidator<Order> orderValidator) {
        this.orderEvaluable = orderEvaluable;
        this.stats = stats;
        this.orderValidator = orderValidator;
    }

    public BigDecimal average(List<Order> orders) {
        List<BigDecimal> numbers = orders.stream()
                .map(orderEvaluable::evaluate)
                .collect(Collectors.toList());
        return stats.mean(numbers);
    }

    public boolean isValid(Order order) {
        return orderValidator.isValid(order);
    }
}
