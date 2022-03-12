package am.narekb.hyeli;

public interface CodeConvention {
    String getGetterName(String fieldName);
    String getSetterName(String fieldName);
    String getFieldName(String methodName);
}
