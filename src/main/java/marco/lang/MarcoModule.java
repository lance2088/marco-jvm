package marco.lang;

import java.util.HashMap;
import java.util.Map;

public class MarcoModule extends MarcoValue {
    private Map<String, MarcoObject> exports = new HashMap<>();

    public MarcoModule() {
    }

    @Override
    public String typeName() {
        return "Module";
    }

    @Override
    public boolean isList() {
        return false;
    }

    @Override
    public String convertToString() {
        return toString();
    }

    public void export(String name, MarcoObject value) {
        exports.put(name, value);
    }

    public MarcoObject getMember(MarcoName name) {
        return exports.get(name.getValue());
    }

    public boolean hasExport(MarcoName name) {
        return exports.containsKey(name.getValue());
    }
}
