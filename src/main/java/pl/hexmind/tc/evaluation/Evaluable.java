package pl.hexmind.tc.evaluation;

import java.math.BigDecimal;

public interface Evaluable<P> {

    BigDecimal evaluate(P p);
}
