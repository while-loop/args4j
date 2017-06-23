package com.github.whileloop.args4j;

public interface Converter<T> {
    T convert(ConvertFactory factory, Class<T> enclosingClass, String value);
    Class<?>[] getConvertClass();
}
