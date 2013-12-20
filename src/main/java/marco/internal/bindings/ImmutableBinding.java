package marco.internal.bindings;

import marco.lang.MarcoObject;
import marco.lang.exception.ImmutabilityError;

public class ImmutableBinding extends Binding {
    public ImmutableBinding(String var, MarcoObject value) {
        super(var, value);
    }

    @Override
    public void mutate(MarcoObject value) {
        throw new ImmutabilityError(this);
    }
}
