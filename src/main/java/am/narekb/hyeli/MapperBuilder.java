package am.narekb.hyeli;

public class MapperBuilder<S, D> {
    private final Mapper<S, D> mapper;

    public MapperBuilder() {
        this.mapper = new Mapper<S, D>();
        this.mapper.setDebugMode(false);
        this.mapper.setConvention(new CamelCaseConvention());
    }

    public Mapper<S, D> build() {
        return this.mapper;
    }

    public MapperBuilder<S, D> setDebugMode(boolean debugMode) {
        this.mapper.setDebugMode(debugMode);
        return this;
    }

    public MapperBuilder<S, D> addMapping(Mapping<S, D> mapping) {
        this.mapper.addMapping(mapping);
        return this;
    }

    /* Template for all subsequent methods
    public MapperBuilder setSomething() {
        // Do something
        return this;
    }
     */
}
