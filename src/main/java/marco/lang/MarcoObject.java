package marco.lang;

import marco.runtime.Environment;

public abstract class MarcoObject {
    private String fileName;
    private Integer startLine;

    public abstract MarcoObject __eval(Environment dynamic);

    public abstract boolean isList();

    public abstract String convertToString();

    @Override
    public String toString() {
        return convertToString();
    }

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

    public boolean isNil() {
        return false;
    }

    public boolean isPair() {
        return false;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getStartLine() {
        return startLine;
    }
}
