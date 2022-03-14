package am.narekb;

import am.narekb.hyeli.Mapper;
import am.narekb.hyeli.MapperBuilder;
import am.narekb.hyeli.dummies.DummyDestination;
import am.narekb.hyeli.dummies.DummySource;

public class App 
{
    public static void main( String[] args )
    {
        Mapper mapper = new MapperBuilder()
                .setDebugMode(true)
//                .addMapping((DummySource src, DummyDestination dest) -> {
//                    dest.setSomeOtherFloatField(src.getTestFloatField());
//                    return dest;
//                })
                .build();

        DummySource source = new DummySource("Test String", 13, 1.3f);
        DummyDestination dest = mapper.map(source, DummyDestination.class);
    }
}
