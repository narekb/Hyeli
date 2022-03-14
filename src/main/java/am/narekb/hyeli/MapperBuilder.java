package am.narekb.hyeli;

public class MapperBuilder {
    private final Mapper mapper;

    public MapperBuilder() {
        this.mapper = new Mapper();
        this.mapper.setDebugMode(false);
        this.mapper.setConvention(new CamelCaseConvention());
    }

    public Mapper build() {
        return this.mapper;
    }

    public MapperBuilder setDebugMode(boolean debugMode) {
        this.mapper.setDebugMode(debugMode);
        return this;
    }

    public <S, D> MapperBuilder addMapping(Mapping<S, D> mapping) {
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
