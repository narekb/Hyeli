package am.narekb.hyeli;

import am.narekb.hyeli.convention.CamelCaseConvention;

public class MapperBuilder {
    private MapperBuilder() {}

    public static <S, D> Mapper<S, D> init(S srcObj, Class<D> destClass) {
        Mapper<S, D> mapper = new Mapper<>(srcObj, destClass);
        mapper.setConvention(new CamelCaseConvention());    // Default convention is camelcase
        return mapper;
    }
}
