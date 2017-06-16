package com.github.whileloop.args4j;

public interface Converter<T> {
    T convert(String value);
    Class getConvertClass();
}
