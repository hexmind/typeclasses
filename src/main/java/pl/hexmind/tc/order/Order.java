package pl.hexmind.tc.order;

import lombok.NonNull;
import lombok.Value;

import javax.validation.Valid;
import java.util.List;

/**
 * All interface implementations in one file only for demonstration purposes.
 */
public interface Order {

    @Value
    class GeneralOrder implements Order {
        @NonNull
        private String number;
        @NonNull
        @Valid
        private List<Product> products;
    }

    @Value
    class ComplexOrder implements Order {
        @NonNull
        @Valid
        private List<Order> orders;
    }

    @Value
    class CanceledOrder implements Order {
        @NonNull
        @Valid
        private Order canceled;
    }
}
