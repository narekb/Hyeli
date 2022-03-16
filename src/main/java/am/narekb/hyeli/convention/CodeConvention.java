package am.narekb.hyeli.convention;

public interface CodeConvention {
    String getGetterName(String fieldName, boolean isBoolean);
    String getSetterName(String fieldName);
    String getFieldName(String methodName);
}
