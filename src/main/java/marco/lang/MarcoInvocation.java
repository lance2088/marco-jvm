package marco.lang;

import marco.internal.Environment;

public class MarcoInvocation extends MarcoObject {
    private MarcoRunnable operator;
    private MarcoList arguments;

    public MarcoInvocation(MarcoRunnable operator, MarcoList arguments) {
        this.operator = operator;
        this.arguments = arguments;
    }

    @Override
    protected MarcoObject doEval(Environment closure, Environment environment) {
        return operator.invoke(closure, arguments);
    }

    @Override
    public String typeName() {
        return "Invocation";
    }

    @Override
    public boolean isList() {
        return false;
    }

    @Override
    public boolean isContinuation() {
        return false;
    }

    @Override
    public String asString() {
        return toString();
    }
}
