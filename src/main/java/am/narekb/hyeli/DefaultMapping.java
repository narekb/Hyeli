package am.narekb.hyeli;

import lombok.extern.java.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
@Log
public class DefaultMapping<S, D> implements Mapping<S, D> {
    private CodeConvention convention;

    public DefaultMapping(CodeConvention convention) {
        this.convention = convention;
    }

    @Override
    public D map(S src, D dest) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // Step 1: Get all fields from sourceObj
        Class<S> sourceClass = (Class<S>) src.getClass();
        Class<D> destinationClass = (Class<D>) dest.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();

        // Step 2: Invoke getter methods for all fields
        for(Field f: sourceFields) {
            String getterMethodName = convention.getGetterName(f.getName());
            Method getterMethod = sourceClass.getMethod(getterMethodName);

            Class<?> sourceFieldType = f.getType();
            Object castedObj = sourceFieldType.cast(getterMethod.invoke(src));

            // Step 3: Invoke setter methods on destination object
            String setterMethodName = convention.getSetterName(f.getName());
            try {
                Method setterMethod = destinationClass.getMethod(setterMethodName,  castedObj.getClass());
                setterMethod.invoke(dest, castedObj);
            } catch (NoSuchMethodException exception) {
                // Ignore missing method.
                log.warning("Setter method " + setterMethodName + " does not exist. Field is not mapped. Make sure you implement a custom mapping for this field.");
            }
        }
        return dest;
    }
}
