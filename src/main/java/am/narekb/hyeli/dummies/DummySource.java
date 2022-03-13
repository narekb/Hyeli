package am.narekb.hyeli.dummies;

public class DummySource {
    private String testStrField;
    private Integer testIntField;
    private Float testFloatField;

    public DummySource(String testStrField, Integer testIntField, Float testFloatField) {
        this.testStrField = testStrField;
        this.testIntField = testIntField;
        this.testFloatField = testFloatField;
    }

    public String getTestStrField() {
        return testStrField;
    }

    public void setTestStrField(String testStrField) {
        this.testStrField = testStrField;
    }

    public void setTestIntField(Integer testIntField) {
        this.testIntField = testIntField;
    }

    public Integer getTestIntField() {
        return testIntField;
    }

    public Float getTestFloatField() {
        return testFloatField;
    }

    public void setTestFloatField(Float testFloatField) {
        this.testFloatField = testFloatField;
    }
}
