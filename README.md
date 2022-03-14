# Hyeli

Hyeli (հայելի - mirror) is a simple Java library for object mapping.
In the simple case with minimal configuration, getting started is as easy as...
```
Mapper mapper = new MapperBuilder().build();
DummySource source = new DummySource("Test String", 13);
DummyDestination dest = mapper.map(source, DummyDestination.class);
```
Fields with different names or different types will be skipped.
For these cases, it is possible to add custom mappings with custom mapping logic.
```
Mapper mapper = new MapperBuilder()
    .addMapping((DummySource src, DummyDestination dest) -> {
        dest.setRenamedField(src.getSomeField());
        return dest;
    })
    .build();
DummySource source = new DummySource("Test String", 13);
DummyDestination dest = mapper.map(source, DummyDestination.class);
```

This project is being developed for practice and self-study purposes, with the intention of being a fully usable and reliable library.