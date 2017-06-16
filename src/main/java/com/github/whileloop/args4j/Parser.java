package com.github.whileloop.args4j;


import com.github.whileloop.args4j.annotation.Option;
import com.github.whileloop.args4j.converter.FileConverters;
import com.github.whileloop.args4j.converter.PrimitiveConverters;

import java.lang.reflect.Field;
import java.util.*;


public class Parser {

    private Object _instance;
    private Map<String, String> _shorts;
    private Map<String, String> _longs;
    private List<Converter> _converters;

    public Parser(Object instance, List<Converter> converters) {
        this._instance = instance;
        this._shorts = new HashMap<>();
        this._longs = new HashMap<>();
        this._longs = new HashMap<>();

        this._converters = new ArrayList<>();
        this._converters.addAll(PrimitiveConverters.PRIMITIVE_CONVERTERS);
        this._converters.addAll(FileConverters.FILE_CONVERTERS);

        if (converters != null) {
            this._converters.addAll(converters);
        }
    }

    public void parse(String[] args) {
        this._shorts = OptionParser.parseShortOpts(args);
        this._longs = OptionParser.parseLongOpts(args);

        Arrays.stream(_instance.getClass().getDeclaredFields())
                .filter((Field field) -> field.isAnnotationPresent(Option.class))
                .forEach(this::setField);
    }

    private void setField(Field field) {
        Option op = field.getAnnotation(Option.class);
        Class clazz = field.getType();

        boolean accessible = field.isAccessible();
        // allow modification of private fields
        if (!accessible) {
            field.setAccessible(true);
        }

        try {
            String strVal = String.valueOf(getValue(op, field.get(_instance)));
            // String check is not needed because values are stored as Strings
            Optional<Converter> c = _converters.stream()
                    .filter(converter -> clazz == converter.getConvertClass())
                    .findFirst();

            if (c.isPresent()) {
                field.set(_instance, c.get().convert(strVal));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            // revert back to original accessible state
            field.setAccessible(accessible);
        }
    }

    private Object getValue(Option op, Object defaultVal) {

        if (_shorts.containsKey(op.shortOpt())) {
            defaultVal = _shorts.get(op.shortOpt());
        }

        if (_longs.containsKey(op.longOpt())) {
            defaultVal = _longs.get(op.longOpt());
        }

        return defaultVal;
    }

    public static void parseArgs(Object instance, String[] args) {
        new Parser(instance, null).parse(args);
    }

    public static class Builder {
        private List<Converter> _converters = new ArrayList<>();
        private Object _instance;

        public Builder(Object instance) {
            this._instance = instance;
        }

        @SafeVarargs
        public final <T> Builder addConverters(Converter<T>... converters) {
            this._converters.addAll(Arrays.asList(converters));
            return this;
        }

        public Parser build() {
            return new Parser(_instance, _converters);
        }
    }

}
