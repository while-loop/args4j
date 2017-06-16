package com.github.whileloop.args4j.converter;


import com.github.whileloop.args4j.Converter;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

public final class NetworkConverters {

    /**
     * Convert host:port strings to InetSocketAddress
     */
    static final Converter<InetSocketAddress> ADDRESS_CONVERTER = new Converter<InetSocketAddress>() {
        @Override
        public InetSocketAddress convert(String value) {
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
        public Class getConvertClass() {
            return InetSocketAddress.class;
        }
    };

    public static final List<Converter> NETWORK_CONVERTERS = Arrays.asList(
            ADDRESS_CONVERTER
    );
}
