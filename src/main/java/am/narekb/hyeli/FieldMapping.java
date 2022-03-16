package am.narekb.hyeli;

import am.narekb.hyeli.function.GetterFunction;
import am.narekb.hyeli.function.SetterFunction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
@AllArgsConstructor
public class FieldMapping<S, D, SF, DF> {
    private GetterFunction<S, SF> sourceGetter;
    private Function<SF, DF> converter;
    private SetterFunction<D, DF> destSetter;

    public FieldMapping(GetterFunction<S, SF> sourceGetter, SetterFunction<D, DF> destSetter) {
        this(sourceGetter, null, destSetter);
    }
}
