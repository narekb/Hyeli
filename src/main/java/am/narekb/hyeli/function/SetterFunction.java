package am.narekb.hyeli.function;

@FunctionalInterface
public interface SetterFunction<D, F> {
    public void set(D destObj, F field);
}
