package pl.hexmind.tc.stats;

import lombok.Builder;

import java.util.Collection;
import java.util.function.BinaryOperator;
import java.util.function.Function;

@Builder
public class Stats<N> {

    private Function<Integer, N> valueOf;
    private N zero;
    private BinaryOperator<N> add;
    private BinaryOperator<N> divide;

    public N mean(Collection<N> numbers) {
        return numbers.size() > 0 ? meanNonEmpty(numbers) : zero;
    }

    private N meanNonEmpty(Collection<N> numbers) {
        N sum = numbers.stream()
                .reduce(zero, add);
        N size = valueOf.apply(numbers.size());
        return divide.apply(sum, size);
    }
}