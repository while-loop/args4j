package com.github.whileloop.args4j.converter;

import com.github.whileloop.args4j.ConvertFactory;
import com.github.whileloop.args4j.Converter;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileConverters {
    static final Converter<File> FILE_CONVERTER = new Converter<File>() {
        @Override
        public File convert(ConvertFactory factory, Type type, String value) {
            return new File(value);
        }

        @Override
        public Class[] getType() {
            return new Class[]{File.class};
        }
    };

    static final Converter<Path> PATH_CONVERTER = new Converter<Path>() {
        @Override
        public Path convert(ConvertFactory factory, Type type, String value) {
            return Paths.get(value);
        }

        @Override
        public Class[] getType() {
            return new Class[]{Path.class};
        }
    };

    public static final List<Converter> FILE_CONVERTERS = Arrays.asList(
            FILE_CONVERTER,
            PATH_CONVERTER
    );
}
