package am.narekb;

import am.narekb.hyeli.MapperBuilder;
import am.narekb.hyeli.dummies.DummyDestination;
import am.narekb.hyeli.dummies.DummySource;

import java.time.LocalDate;

public class App 
{
    public static void main( String[] args )
    {
        DummySource source = new DummySource("Test String", 13, 1.3f);
        DummyDestination dest = MapperBuilder
                .init(source, DummyDestination.class)
                .setConverter("testFloatField", sf -> LocalDate.now())
                .addMapping("testStrField",
                        source1 -> source1.getTestStrField(),
                        (destObj, field) -> destObj.setTestStrField(field))
                .map();
    }
}
