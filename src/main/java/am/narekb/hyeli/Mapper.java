package am.narekb.hyeli;

import lombok.extern.java.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Type variables
// S = Source
// D = Destination
// F = Field type
@Log
public class Mapper {
    private CodeConvention convention;

    private boolean debugMode;

    public Mapper() {
    }

    public <S, D> D map(S sourceObj, Class<D> destinationClass) {
        D mappedObject = null;

        try {
            mappedObject = destinationClass.getDeclaredConstructor().newInstance();
            setFields(sourceObj, mappedObject);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mappedObject;
    }

    @SuppressWarnings("unchecked")
    private <S, D, F> D setFields(S sourceObj, D destinationObj) throws Exception{
        // Step 1: Get all fields from sourceObj
        Class<S> sourceClass = (Class<S>) sourceObj.getClass();
        Class<D> destinationClass = (Class<D>) destinationObj.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();

        if(debugMode) {
            log.info("Number of source fields = " + sourceFields.length);
            log.info("Source class name = " + sourceClass.getName());
            log.info("Destination class name = " + destinationClass.getName());
        }

        // Step 2: Invoke getter methods for all fields
        for(Field f: sourceFields) {
            String getterMethodName = convention.getGetterName(f.getName());
            Method getterMethod = sourceClass.getMethod(getterMethodName);

            Class<?> sourceFieldType = f.getType();
            F castedObj = (F) sourceFieldType.cast(getterMethod.invoke(sourceObj));

            // Step 3: Invoke setter methods on destination object
            String setterMethodName = convention.getSetterName(f.getName());
            Method setterMethod = destinationClass.getMethod(setterMethodName,  castedObj.getClass());
            setterMethod.invoke(destinationObj, castedObj);
        }

        return destinationObj;
    }

    public CodeConvention getConvention() {
        return convention;
    }

    public void setConvention(CodeConvention convention) {
        this.convention = convention;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
