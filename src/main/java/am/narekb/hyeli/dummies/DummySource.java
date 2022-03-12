package am.narekb.hyeli.dummies;

public class DummySource {
    private String testStrField;
    private Integer testIntField;

    public DummySource(String testStrField, int testIntField) {
        this.testStrField = testStrField;
        this.testIntField = testIntField;
    }

    public String getTestStrField() {
        return testStrField;
    }

    public void setTestStrField(String testStrField) {
        this.testStrField = testStrField;
    }

    public int getTestIntField() {
        return testIntField;
    }

    public void setTestIntField(int testIntField) {
        this.testIntField = testIntField;
    }
}
