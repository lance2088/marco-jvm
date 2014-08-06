package marco.lang;

import marco.runtime.Environment;

public abstract class MarcoObject {
    protected String fileName;
    protected Integer startLine;

    public abstract MarcoObject __eval(Environment dynamic);

    @Override
    public String toString() {
        return typeName();
    }

    public abstract String typeName();

    public abstract boolean isList();

    public abstract String convertToString();

    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isContinuation() {
        return false;
    }

    public MarcoObject resolve() {
        return this;
    }
}
