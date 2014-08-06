package marco.lang.functions;

import marco.runtime.Cast;
import marco.runtime.Environment;
import marco.lang.*;

public class _while extends MarcoNativeBlock {
    @Override
    public MarcoObject invoke(Environment closure, Environment dynamic) {
        MarcoBlock cond = Cast.toBlock(closure.lookUp("cond"));
        MarcoBlock body = Cast.toBlock(closure.lookUp("body"));

        while (Cast.toBoolean(cond.invoke(dynamic, dynamic)) == MarcoBoolean.TRUE) {
            body.invoke(dynamic, dynamic);
        }

        return MarcoNil.NIL;
    }
}
