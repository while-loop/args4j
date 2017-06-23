package com.github.whileloop.args4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertFactory {

    private Map<Class, Converter<?>> _converters = new HashMap<>();

    void addAll(List<Converter> converters) {
        for (Converter c : converters) {
            for (Class cl : c.getConvertClass()) {
                _converters.put(cl, c);
            }
        }
    }

    public Object convert(Class clazz, String value) {
        if (_converters.containsKey(clazz)) {
            return _converters.get(clazz).convert(this, clazz, value);
        }

        return null;
    }
}
