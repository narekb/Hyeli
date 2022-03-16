package am.narekb.hyeli.convention;

public class CamelCaseConvention implements CodeConvention{
    private final String GETTER_PREFIX = "get";
    private final String IS_PREFIX = "is";
    private final String SETTER_PREFIX = "set";

    @Override
    public String getGetterName(String fieldName, boolean isBoolean) {
        // field --> getField
        return isBoolean ? IS_PREFIX + upperCase(fieldName) : GETTER_PREFIX + upperCase(fieldName);
    }

    @Override
    public String getSetterName(String fieldName) {
        // field --> setField
        return SETTER_PREFIX + upperCase(fieldName);
    }

    @Override
    public String getFieldName(String methodName) {
        // Note special case: A boolean variable getter
        // might be prefixed with "is", not "get"
        if(methodName.startsWith(GETTER_PREFIX) || methodName.startsWith(SETTER_PREFIX)) {
            return lowerCase(methodName.substring(GETTER_PREFIX.length()));
        } else if(methodName.startsWith(IS_PREFIX)) {
            return lowerCase(methodName.substring(GETTER_PREFIX.length()));
        }

        return methodName; //Unknown case
    }

    private String upperCase(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private String lowerCase(String methodName) {
        return methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
    }
}