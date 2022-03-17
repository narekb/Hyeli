package am.narekb.hyeli;

import am.narekb.hyeli.convention.CodeConvention;
import am.narekb.hyeli.function.GetterFunction;
import am.narekb.hyeli.function.SetterFunction;
import lombok.extern.java.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Log
@SuppressWarnings("unchecked")
public class Mapper<S, D> {
    private Map<String, FieldMapping<S, D, ?, ?>> fieldMappings = new HashMap<>();
    private CodeConvention convention;
    private S srcObj;
    private D destObj;


    Mapper(S srcObj, Class<D> destClass) {
        try {
            this.srcObj = srcObj;
            this.destObj = destClass.getDeclaredConstructor().newInstance();

            initDefaultMappings((Class<S>) srcObj.getClass(), destClass);
        } catch (Exception e) {
            log.warning("Failed to instantiate destination class: " + e.getMessage());
        }

    }

    private void initDefaultMappings(Class<S> srcClass, Class<D> destClass) throws NoSuchMethodException {
        Field[] fields = srcClass.getDeclaredFields();
        for(Field field : fields) {
            boolean isFieldBoolean = field.getType().equals(Boolean.class) || field.getType().equals(boolean.class);
            String fieldName = field.getName();

            addMapping(fieldName,
                    (src) -> {
                        try {
                            Method getter = src.getClass().getMethod(convention.getGetterName(fieldName, isFieldBoolean));
                            return getter.invoke(src);
                        } catch (Exception e) {
                            log.warning("Failed to call getter method for field " + fieldName + ": " + e.getMessage());
                        }
                        return null;
                    },
                    (dest, value) -> {
                        try {
                            Method setter = dest.getClass().getMethod(convention.getSetterName(fieldName), value.getClass());
                            setter.invoke(dest, value);
                        } catch (Exception e) {
                            log.warning("Failed to call setters method for field " + fieldName + ": " + e.getMessage());
                        }
                    });
        }
    }

    public <SF, DF> D map() {
        for(String fieldName : fieldMappings.keySet()) {
            FieldMapping<S, D, SF, DF> mapping = (FieldMapping<S, D, SF, DF>) fieldMappings.get(fieldName);

            SF fieldVal = mapping.getSourceGetter().get(srcObj);
            DF convertedValue = null;
            try {
                convertedValue = mapping.getConverter() == null ? (DF) fieldVal : mapping.getConverter().apply(fieldVal);
            } catch (ClassCastException e) {
                log.warning("Failed to cast field " + fieldName + " to type in destination. Please use a custom mapping.");
            }

            mapping.getDestSetter().set(destObj, convertedValue);
        }

        return destObj;
    }

    public void setConvention(CodeConvention convention) {
        this.convention = convention;
    }

    public <SF, DF> Mapper<S, D> addMapping(String fieldName,
                                    GetterFunction<S, SF> getter,
                                    Function<SF, DF> converter,
                                    SetterFunction<D, DF> setter) {

        FieldMapping<S, D, SF, DF> fieldMapping = new FieldMapping<>(getter, converter, setter);
        fieldMappings.put(fieldName, fieldMapping);

        return this;
    }

    public <SF, DF extends SF> Mapper<S, D> addMapping(String fieldName,
                                    GetterFunction<S, SF> getter,
                                    SetterFunction<D, DF> setter) {

        this.addMapping(fieldName, getter, null ,setter);
        return this;
    }

    public void skip(String fieldName) {
        fieldMappings.remove(fieldName);
    }

    public <SF, DF> Mapper<S, D> setConverter(String fieldName, Function<SF, DF> converter) {
        FieldMapping<S, D, SF, DF> mapping = (FieldMapping<S, D, SF, DF>) fieldMappings.get(fieldName);
        if(mapping != null) {
            mapping.setConverter(converter);
        }

        return this;
    }
}
