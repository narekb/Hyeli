### Introduction

*Hyeli* (հայելի - mirror) is a simple Java library for object mapping.
In the simple case with minimal configuration, getting started is as easy as...
```java
// Assuming you have Entity and EntityDto classes.
EntityDto dto = MapperBuilder
                .init(entity, EntityDto.class)
                .map();
```
### Customization
Hyeli looks up fields by matching names and types. Any differences in these attributes can be reconciled with custom mappings and converters.

Use **converters** if the field name is the same in both clases, but the types are different 
```java
EntityDto dto = MapperBuilder
                .init(entity, EntityDto.class)
                .setConverter("fieldThatNeedsConversion", fieldValue -> fieldValue.toString())
                .map();
```

Use **custom mappings** if the field names or getter/setter functions are different.
You can add a custom mapping with or without a converter:
```java
EntityDto dto = MapperBuilder
                .init(entity, EntityDto.class)
                .addMapping("fieldWithCustomMapping",
                    Entity::getFieldWithCustomMapping,
                    val -> val.toString(),  // Optional converter if field types don't match
                    EntityDto::setSomeOtherField)
                .map();
```

In cases where a field does not need to be mapped, you can skip it:
```java
EntityDto dto = MapperBuilder
                .init(entity, EntityDto.class)
                .skip("unnecessaryField")
                .map();
```
### Conventions
Hyeli deduces getter and setter method names based on code conventions. By default, an implementation of camel case convention is included when a mapper is initialized.
You can implement your own conventions by implementing the `CodeConvention` interface and passing it to the mapper through the `setConvention()` method:
```java
EntityDto dto = MapperBuilder
        .addConvention(new CodeConvention() {
            // Implement methods here...
        })
        .init(entity, EntityDto.class)
        .map();
```

This project is being developed for practice and self-study purposes, with the intention of being a fully usable and reliable library.