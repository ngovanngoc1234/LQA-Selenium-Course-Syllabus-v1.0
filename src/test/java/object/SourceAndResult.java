package object;

import java.io.Serializable;

public class SourceAndResult implements Serializable {
    String source;
    String result;

    public SourceAndResult() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public SourceAndResult(String source, String result) {
        this.source = source;
        this.result = result;
    }
}
