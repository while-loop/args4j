package com.github.whileloop.args4j.converter;


import com.github.whileloop.args4j.Converter;

import java.util.Arrays;
import java.util.List;

public final class PrimitiveConverters {

    static final Converter<String> STRING_CONVERTER = new Converter<String>() {
        @Override
        public String convert(String value) {
            return value;
        }

        @Override
        public Class<String> getConvertClass() {
            return String.class;
        }
    };

    static final Converter<Integer> INTEGER_CONVERTER = new Converter<Integer>() {
        @Override
        public Integer convert(String value) {
            return Integer.parseInt(value);
        }

        @Override
        public Class<Integer> getConvertClass() {
            return Integer.class;
        }
    };

    static final Converter<Short> SHORT_CONVERTER = new Converter<Short>() {
        @Override
        public Short convert(String value) {
            return Short.parseShort(value);
        }

        @Override
        public Class<Short> getConvertClass() {
            return Short.class;
        }
    };

    static final Converter<Double> DOUBLE_CONVERTER = new Converter<Double>() {
        @Override
        public Double convert(String value) {
            return Double.parseDouble(value);
        }

        @Override
        public Class<Double> getConvertClass() {
            return Double.class;
        }
    };

    static final Converter<Boolean> BOOLEAN_CONVERTER = new Converter<Boolean>() {
        @Override
        public Boolean convert(String value) {
            return Boolean.parseBoolean(value) || (value != null && value.equals("1"));
        }

        @Override
        public Class<Boolean> getConvertClass() {
            return Boolean.class;
        }
    };

    static final Converter<Long> LONG_CONVERTER = new Converter<Long>() {
        @Override
        public Long convert(String value) {
            return Long.parseLong(value);
        }

        @Override
        public Class<Long> getConvertClass() {
            return Long.class;
        }
    };

    static final Converter<Character> CHARACTER_CONVERTER = new Converter<Character>() {
        @Override
        public Character convert(String value) {
            return value.charAt(0);
        }

        @Override
        public Class<Character> getConvertClass() {
            return Character.class;
        }
    };

    static final Converter<Float> FLOAT_CONVERTER = new Converter<Float>() {
        @Override
        public Float convert(String value) {
            return Float.parseFloat(value);
        }

        @Override
        public Class<Float> getConvertClass() {
            return Float.class;
        }
    };

    static final Converter<Byte> BYTE_CONVERTER = new Converter<Byte>() {
        @Override
        public Byte convert(String value) {
            return Byte.parseByte(value);
        }

        @Override
        public Class<Byte> getConvertClass() {
            return Byte.class;
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
