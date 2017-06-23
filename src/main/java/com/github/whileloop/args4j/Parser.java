package com.github.whileloop.args4j;


import com.github.whileloop.args4j.annotation.Option;
import com.github.whileloop.args4j.annotation.Program;
import com.github.whileloop.args4j.converter.FileConverters;
import com.github.whileloop.args4j.converter.MiscConverters;
import com.github.whileloop.args4j.converter.PrimitiveConverters;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;


public class Parser {

    private Object _instance;
    private Map<String, String> _shorts;
    private Map<String, String> _longs;

    // TODO change from list to Map to be able to override converters
    private ConvertFactory _converters;
    private Field[] _fields;
    private boolean _exit = true;

    /**
     * Parse an instance's static and non-static member fields
     *
     * @param instance
     */
    public Parser(Object instance) {
        this._instance = instance;
        this._shorts = new HashMap<>();
        this._longs = new HashMap<>();
        this._longs = new HashMap<>();

        this._converters = new ConvertFactory();
        this._converters.addAll(PrimitiveConverters.PRIMITIVE_CONVERTERS);
        this._converters.addAll(FileConverters.FILE_CONVERTERS);
        this._converters.addAll(MiscConverters.MISC_CONVERTERS);

        Class clazz = (this._instance instanceof Class) ? (Class) this._instance : this._instance.getClass();

        this._fields = Arrays.stream(clazz.getDeclaredFields())
                .filter((Field f) -> f.isAnnotationPresent(Option.class))
                .toArray(Field[]::new);
    }

    public void printHelp() {
        Program program = _instance.getClass().getAnnotation(Program.class);
        if (program != null) {
            System.out.printf("Usage: %s %s%n", program.name(), program.usage());
        }

        System.out.println("Options:");
        for (Field f : _fields) {
            Option option = f.getAnnotation(Option.class);
            boolean accessible = f.isAccessible();
            if (!accessible) {
                f.setAccessible(true);
            }

            System.out.printf(" %s%s, %s%s\t%s", OptionParser.SHORT_SEP, option.shortOpt(),
                    OptionParser.LONG_SEP, option.longOpt(),
                    option.desc());

            try {
                String def = getValue(option, null);
                if (def != null && !def.equals("null")) {
                    System.out.printf("\t(default: %s)", f.get(_instance));
                }
                System.out.println();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                f.setAccessible(accessible);
            }

        }
        System.out.println();
    }

    public void parse(String[] args) {
        OptionParser op = new OptionParser(_fields, args);
        this._shorts = op.parseShortOpts();
        this._longs = op.parseLongOpts();

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
        Type type = field.getGenericType();

        boolean accessible = field.isAccessible();
        // allow modification of private fields
        if (!accessible) {
            field.setAccessible(true);
        }

        try {
            String strVal = getValue(op, field.get(_instance));
            // String check is not needed because values are stored as Strings

            Object converted = _converters.convert(type, strVal);
            System.out.println("converted " + field + " " + converted);
            field.set(_instance, converted);
            System.out.println(field.get(_instance));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            System.err.println("accessing instance var from static Class: " + e.getMessage());
        } finally {
            // revert back to original accessible state
            field.setAccessible(accessible);
        }
    }

    private String getValue(Option op, Object defaultVal) {
        if (_shorts.containsKey(String.valueOf(op.shortOpt()))) {
            defaultVal = _shorts.get(String.valueOf(op.shortOpt()));
        }

        if (_longs.containsKey(op.longOpt())) {
            defaultVal = _longs.get(op.longOpt());
        }

        return String.valueOf(defaultVal);
    }

    /**
     * Parse args with default config and converters
     *
     * @param instance
     * @param args
     */
    public static void parseArgs(Object instance, String[] args) {
        new Parser(instance).parse(args);
    }

    /**
     * Parse args with default config and converters
     *
     * @param instance
     * @param args
     */
    public static void parseArgs(Class instance, String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        new Parser(instance).parse(args);
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
            Parser p = new Parser(_instance);
            if (_converters != null) {
                p._converters.addAll(_converters);
            }

            p._exit = _exit;
            return p;
        }
    }
}
