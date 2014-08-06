package marco.lang.continuation;

import marco.lang.MarcoObject;
import marco.lang.MarcoValue;
import marco.runtime.Environment;

public class EvalContinuation extends MarcoValue {
    private MarcoObject value;
    private Environment environment;

    public EvalContinuation(MarcoObject value, Environment environment) {
        this.value = value;
        this.environment = environment;
    }

    @Override
    public String typeName() {
        return "Continuation";
    }

    @Override
    public boolean isList() {
        return false;
    }

    @Override
    public String convertToString() {
        return null;
    }

    @Override
    public boolean isContinuation() {
        return true;
    }

    @Override
    public MarcoObject resolve() {
        return value.__eval(environment);
    }
}
