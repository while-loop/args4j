package com.github.whileloop.args4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertFactory {

    private Map<Type, Converter<?>> _converters = new HashMap<>();

    void addAll(List<Converter> converters) {
        for (Converter c : converters) {
            for (Type t : c.getConvertClass()) {
                _converters.put(t, c);
            }
        }
    }

    /**
     * @param clazz
     * @param value
     * @return
     * @deprecated use {@link #convert(Type, String)}
     */
    public Object convert(Class clazz, String value) {
        if (_converters.containsKey(clazz)) {
            return _converters.get(clazz).convert(this, clazz, value);
        }

        return null;
    }

    public Object convert(Type type, String value) {
        Type tt = type;
        if (type instanceof ParameterizedType){
            tt = ((ParameterizedType)type).getRawType();
        }

        if (_converters.containsKey(tt)) {
            return _converters.get(tt).convert(this, type, value);
        }

        return null;
    }
}
