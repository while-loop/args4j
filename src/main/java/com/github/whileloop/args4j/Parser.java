package com.github.whileloop.args4j;


import com.github.whileloop.args4j.annotation.Option;
import com.github.whileloop.args4j.annotation.Program;
import com.github.whileloop.args4j.converter.FileConverters;
import com.github.whileloop.args4j.converter.PrimitiveConverters;

import java.lang.reflect.Field;
import java.util.*;


public class Parser {

    private Object _instance;
    private Map<String, String> _shorts;
    private Map<String, String> _longs;
    private List<Converter> _converters;
    private Field[] _fields;
    private boolean _exit = true;

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

        this._fields = Arrays.stream(_instance.getClass().getDeclaredFields())
                .filter((Field f) -> f.isAnnotationPresent(Option.class))
                .toArray(Field[]::new);
    }

    public void printHelp() {
        Program program = _instance.getClass().getAnnotation(Program.class);
        if (program != null){
            System.out.printf("Usage: %s %s%n", program.name(), program.usage());
        }

        System.out.println("Options:");
        for (Field f : _fields) {
            Option option = f.getAnnotation(Option.class);
            System.out.printf(" %s%s, %s%s\t%s%n", OptionParser.SHORT_SEP, option.shortOpt(),
                    OptionParser.LONG_SEP, option.longOpt(),
                    option.desc());
        }
        System.out.println();
    }

    public void parse(String[] args) {
        this._shorts = OptionParser.parseShortOpts(_fields, args);
        this._longs = OptionParser.parseLongOpts(_fields, args);

        printHelp();
        if (this._shorts.containsKey("h") || this._longs.containsKey("help")) {
            printHelp();
            if (_exit) {
                System.exit(0);
            }
        }

        for (Field _field : _fields) {
            setField(_field);
        }
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
            String strVal = getValue(op, field.get(_instance));
            // String check is not needed because values are stored as Strings
            Optional<Converter> c = _converters.stream()
                    .filter(converter -> clazz == converter.getConvertClass())
                    .findFirst();

            if (c.isPresent()) {
                field.set(_instance, c.get().convert(strVal));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            // revert back to original accessible state
            field.setAccessible(accessible);
        }
    }

    private String getValue(Option op, Object defaultVal) {

        if (_shorts.containsKey(op.shortOpt())) {
            defaultVal = _shorts.get(op.shortOpt());
        }

        if (_longs.containsKey(op.longOpt())) {
            defaultVal = _longs.get(op.longOpt());
        }

        return String.valueOf(defaultVal);
    }

    public static void parseArgs(Object instance, String[] args) {
        new Parser(instance, null).parse(args);
    }

    public void setOnExit(boolean onExit) {
        this._exit = onExit;
    }

    public static class Builder {
        private List<Converter> _converters = new ArrayList<>();
        private Object _instance;
        private boolean _exit = true;

        public Builder(Object instance) {
            this._instance = instance;
        }

        @SafeVarargs
        public final <T> Builder addConverters(Converter<T>... converters) {
            this._converters.addAll(Arrays.asList(converters));
            return this;
        }

        public Builder setExitOnHelp(boolean exit) {
            this._exit = exit;
            return this;
        }

        public Parser build() {
            Parser p = new Parser(_instance, _converters);
            p.setOnExit(_exit);
            return p;
        }
    }
}
