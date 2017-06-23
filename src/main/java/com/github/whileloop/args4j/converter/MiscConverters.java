package com.github.whileloop.args4j.converter;

import com.github.whileloop.args4j.ConvertFactory;
import com.github.whileloop.args4j.Converter;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;


public class MiscConverters {

    static final Converter<? extends Object[]> ARRAY_CONVERTER = new Converter<Object[]>() {
        @Override
        public Object[] convert(ConvertFactory factory, Class<Object[]> clazz, String value) {
            System.out.println("IM HERE");
            String[] tokens = value.split(",");
            Object[] c = Arrays.stream(tokens)
                    .map(token -> factory.convert(clazz.getComponentType(), token))
                    .toArray();
            return c;
        }

        @Override
        public Class[] getConvertClass() {
            return new Class[]{Object[].class,
                    int[].class, double[].class,
                    float[].class, short[].class,
                    String[].class, long[].class,
                    boolean[].class, char.class};
        }
    };

    /**
     * Convert host:port strings to InetSocketAddress
     */
    static final Converter<InetSocketAddress> ADDRESS_CONVERTER = new Converter<InetSocketAddress>() {
        @Override
        public InetSocketAddress convert(ConvertFactory factory, Class<InetSocketAddress> enclosingClass, String value) {
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
        public Class[] getConvertClass() {
            return new Class[]{InetSocketAddress.class};
        }
    };

    public static final List<Converter> MISC_CONVERTERS = Arrays.asList(
            ARRAY_CONVERTER,
            ADDRESS_CONVERTER
    );
}
