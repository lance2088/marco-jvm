package marco.lang.functions.number;

import marco.lang.MarcoBoolean;
import marco.lang.MarcoNumber;
import marco.lang.MarcoObject;

public class greaterthanorequal extends BinaryOperatorBody {
    @Override
    protected MarcoObject invoke(MarcoNumber v1, MarcoNumber v2) {
        return MarcoBoolean.from(v1.getValue().compareTo(v2.getValue()) >= 0);
    }
}
