package am.narekb.hyeli;

import lombok.extern.java.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Type variables
// S = Source
// D = Destination
@Log
public class Mapper {
    private CodeConvention convention;
    private List<Mapping<?, ?>> mappings = new ArrayList<>();
    private boolean debugMode;

    public Mapper() {
    }

    @SuppressWarnings("unchecked")
    public <S, D> D map(S sourceObj, Class<D> destinationClass) {
        D mappedObject = null;
        mappings.add(new DefaultMapping<S, D>(convention));
        try {
            mappedObject = destinationClass.getDeclaredConstructor().newInstance();
            for(Mapping<?, ?> mapping: mappings) {
                // Is it fine to explicitly cast Mapping<?, ?> to Mapping<S, D>?
                mappedObject = ((Mapping<S, D>) mapping).map(sourceObj, mappedObject);
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

    public <S, D> void addMapping(Mapping<S, D> mapping) {
        this.mappings.add(mapping);
    }
}
