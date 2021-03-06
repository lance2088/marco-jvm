package marco.lang.functions.number;

import marco.runtime.Environment;
import marco.lang.MarcoFunction;

import java.util.Arrays;

public class BinaryOperator extends MarcoFunction {
    public BinaryOperator(Environment environment, BinaryOperatorBody body) {
        super(environment, Arrays.asList("v1", "v2"), body);
    }
}
