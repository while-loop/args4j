package com.github.whileloop.args4j.converter;


import com.github.whileloop.args4j.ConvertFactory;
import com.github.whileloop.args4j.Converter;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public final class PrimitiveConverters {

    static final Converter<String> STRING_CONVERTER = new Converter<String>() {
        @Override
        public String convert(ConvertFactory factory, Type type, String value) {
            return value;
        }

        @Override
        public Class[] getType() {
            return new Class[]{String.class};
        }
    };

    static final Converter<Integer> INTEGER_CONVERTER = new Converter<Integer>() {
        @Override
        public Integer convert(ConvertFactory factory, Type type, String value) {
            return Integer.parseInt(value);
        }

        @Override
        public Class[] getType() {
            return new Class[]{Integer.class, int.class};
        }
    };

    static final Converter<Short> SHORT_CONVERTER = new Converter<Short>() {
        @Override
        public Short convert(ConvertFactory factory, Type type, String value) {
            return Short.parseShort(value);
        }

        @Override
        public Class[] getType() {
            return new Class[]{Short.class, short.class};
        }
    };

    static final Converter<Double> DOUBLE_CONVERTER = new Converter<Double>() {
        @Override
        public Double convert(ConvertFactory factory, Type type, String value) {
            return Double.parseDouble(value);
        }

        @Override
        public Class[] getType() {
            return new Class[]{double.class, Double.class};
        }
    };

    static final Converter<Boolean> BOOLEAN_CONVERTER = new Converter<Boolean>() {
        @Override
        public Boolean convert(ConvertFactory factory, Type type, String value) {
            return Boolean.parseBoolean(value) || (value != null && value.equals("1"));
        }

        @Override
        public Class[] getType() {
            return new Class[]{boolean.class, Boolean.class};
        }
    };

    static final Converter<Long> LONG_CONVERTER = new Converter<Long>() {
        @Override
        public Long convert(ConvertFactory factory, Type type, String value) {
            return Long.parseLong(value);
        }

        @Override
        public Class[] getType() {
            return new Class[]{Long.class, long.class};
        }
    };

    static final Converter<Character> CHARACTER_CONVERTER = new Converter<Character>() {
        @Override
        public Character convert(ConvertFactory factory, Type type, String value) {
            if (value == null || value.length() != 1) {
                throw new IllegalArgumentException("value given is not length 1");
            }
            return value.charAt(0);
        }

        @Override
        public Class[] getType() {
            return new Class[]{Character.class, char.class};
        }
    };

    static final Converter<Float> FLOAT_CONVERTER = new Converter<Float>() {
        @Override
        public Float convert(ConvertFactory factory, Type type, String value) {
            return Float.parseFloat(value);
        }

        @Override
        public Class[] getType() {
            return new Class[]{float.class, Float.class};
        }
    };

    static final Converter<Byte> BYTE_CONVERTER = new Converter<Byte>() {
        @Override
        public Byte convert(ConvertFactory factory, Type type, String value) {
            return Byte.parseByte(value);
        }

        @Override
        public Class[] getType() {
            return new Class[]{byte.class, Byte.class};
        }
    };

    // TODO Array/List converters
    public static final List<Converter> PRIMITIVE_CONVERTERS = Arrays.asList(
            BYTE_CONVERTER,
            CHARACTER_CONVERTER,
            STRING_CONVERTER,

            BOOLEAN_CONVERTER,

            SHORT_CONVERTER,
            INTEGER_CONVERTER,
            LONG_CONVERTER,

            FLOAT_CONVERTER,
            DOUBLE_CONVERTER
    );

}
