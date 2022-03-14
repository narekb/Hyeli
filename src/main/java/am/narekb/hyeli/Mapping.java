package am.narekb.hyeli;

@FunctionalInterface
public interface Mapping<S, D> {
    <F> D map(S src, D dest) throws Exception;
}
