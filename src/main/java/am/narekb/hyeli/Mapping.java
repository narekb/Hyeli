package am.narekb.hyeli;

@FunctionalInterface
public interface Mapping<S, D> {
//    private Function<S, ?> source;
//    private Function<?, ?> converter;
//    private SetterFunction<?, D> destination;
    public D map(S src, D dest) throws Exception;
}
