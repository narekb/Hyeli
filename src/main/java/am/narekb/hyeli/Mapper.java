package am.narekb.hyeli;

import lombok.extern.java.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Type variables
// S = Source
// D = Destination
// F = Field type
@Log
public class Mapper<S, D> {
    private CodeConvention convention;
    private List<Mapping<S, D>> mappings = new ArrayList<>();
    private boolean debugMode;

    public Mapper() {
    }

    public D map(S sourceObj, Class<D> destinationClass) {
        D mappedObject = null;
        mappings.add(new DefaultMapping<>(convention));
        try {
            mappedObject = destinationClass.getDeclaredConstructor().newInstance();
            for(Mapping<S, D> mapping: mappings) {
                mappedObject = mapping.map(sourceObj, mappedObject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mappedObject;
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

    public void addMapping(Mapping<S, D> mapping) {
        this.mappings.add(mapping);
    }
}
