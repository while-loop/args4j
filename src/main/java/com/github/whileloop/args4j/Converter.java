package com.github.whileloop.args4j;

import java.lang.reflect.Type;

public interface Converter<T> {
    T convert(ConvertFactory factory, Type type, String value);

    Type[] getConvertClass();
}
