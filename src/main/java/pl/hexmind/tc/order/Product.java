package pl.hexmind.tc.order;

import lombok.NonNull;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * All interface implementations in one file only for demonstration purposes.
 */
public interface Product {

    @Value
    class Basic implements Product {
        @NonNull
        private final Long id;
        @NonNull
        @Min(0)
        private final BigDecimal price;
    }

    @Value
    class Discounted implements Product {
        @NonNull
        @Valid
        private final Product discounted;
        @DecimalMin("0.0")
        @DecimalMax("1.0")
        private final double discount;
    }

    @Value
    class OutOfStock implements Product {
        @NonNull
        @Valid
        private final Product product;
    }

}