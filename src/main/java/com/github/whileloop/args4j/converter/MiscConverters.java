package com.github.whileloop.args4j.converter;

import com.github.whileloop.args4j.ConvertFactory;
import com.github.whileloop.args4j.Converter;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MiscConverters {

    static final Converter<Object> ARRAY_CONVERTER = new Converter<Object>() {
        @Override
        public Object convert(ConvertFactory factory, Type type, String value) {
            Class componentClass = ((Class) type).getComponentType();
            String[] tokens = value.split(",");

            List<Object> list = new ArrayList<>();
            for (String token : tokens) {
                list.add(factory.convert((Type) componentClass, token));
            }

            int size = list.size();
            Object array = Array.newInstance(componentClass, size);
            for (int i = 0; i < size; i++) {
                Array.set(array, i, list.get(i));
            }
            return array;
        }

        @Override
        public Class[] getType() {
            return new Class[]{Object[].class,
                    int[].class, double[].class,
                    float[].class, short[].class,
                    String[].class, long[].class,
                    boolean[].class, char.class};
        }
    };

    static class ListConverter<T> implements Converter<List<T>> {

        @Override
        public List<T> convert(ConvertFactory factory, Type type, String value) {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalArgumentException("Incorrect type:" + type);
            }
            ParameterizedType pt = (ParameterizedType) type;
            assert pt.getActualTypeArguments().length >= 1;

//            System.out.println(tt);
//            System.out.println(tt.getRawType());
            String[] tokens = value.split(",");

            List<T> list = new ArrayList<>();
            for (String token : tokens) {
                list.add((T) factory.convert(pt.getActualTypeArguments()[0], token));
            }

            return list;
        }

        @Override
        public Class<?>[] getType() {
            return new Class[]{List.class};
        }
    }
//    static final Converter<List> LIST_CONVERTER = new Converter<List>() {
//        @Override
//        public List convert(ConvertFactory factory, Class<List> clazz, String value) {
//            Class componentClass = clazz.getComponentType();
//            Type listType = new TypeToken<Collection>(clazz){}.getType();
//
//            System.out.println(tt);
//            System.out.println(tt.getRawType());
//            String[] tokens = value.split(",");
//
//            List<Object> list = new ArrayList<>();
//            for (String token: tokens) {
//                list.add(factory.convert(componentClass, token));
//            }
//
//            return list;
//        }
//
//        @Override
//        public Class[] getType() {
//            return new Class[]{List.class};
//        }
//};

    /**
     * Convert host:port strings to InetSocketAddress
     */
    static final Converter<InetSocketAddress> ADDRESS_CONVERTER = new Converter<InetSocketAddress>() {
        @Override
        public InetSocketAddress convert(ConvertFactory factory, Type type, String value) {
            String host = "localhost";
            int port = -1;

            if (value.contains(":")) {
                String[] vals = value.split(":");
                if (!vals[0].isEmpty()) { // explicit host given
                    host = vals[0];
                }
                port = Integer.parseInt(vals[1]);
            }

            return new InetSocketAddress(host, port);
        }

        @Override
        public Class[] getType() {
            return new Class[]{InetSocketAddress.class};
        }
    };

    public static final List<Converter> MISC_CONVERTERS = Arrays.asList(
            ARRAY_CONVERTER,
            ADDRESS_CONVERTER,
            // LIST_CONVERTER,
            new ListConverter<>()
    );
}
