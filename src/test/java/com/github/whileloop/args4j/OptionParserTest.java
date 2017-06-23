package com.github.whileloop.args4j;

import com.github.whileloop.args4j.annotation.Option;
import com.github.whileloop.args4j.annotation.StubOption;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class OptionParserTest {
    @Test
    public void parseShortOpts() throws Exception {
        Map<String, String> expected = new HashMap<>();
        expected.put("v", null);
        expected.put("6", null);
        expected.put("r", null);
        expected.put("i", "eth0");

        class yolo {
            @Option(longOpt = "verbose", shortOpt = 'v')
            boolean verbose;

            @Option(longOpt = "ipv6", shortOpt = '6')
            boolean ipv6;

            @Option(longOpt = "reconnect", shortOpt = 'r')
            boolean reconnect;

            @Option(longOpt = "iface", shortOpt = 'i')
            String iface = "lo";
        }

        OptionParser op = new OptionParser(Arrays.stream(new yolo().getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Option.class))
                .toArray(Field[]::new), "-vr6 --fakeer=no -i eth0 --fake-long test".split(" "));

        Map<String, String> actual = op.parseShortOpts();
        System.out.println(actual);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void parseLongOpts() throws Exception {
    }

    @Test
    public void getFieldByShort() throws Exception {
        class yolo {
            @Option(longOpt = "verbose", shortOpt = 'v')
            boolean verbose;
        }

        OptionParser op = new OptionParser(Arrays.stream(new yolo().getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Option.class))
                .toArray(Field[]::new), new String[]{});

        Optional<Field> f = op.getFieldByShort("v");
        assertTrue(f.isPresent());
        assertEquals("verbose", f.get().getName());
        assertEquals(boolean.class, f.get().getType());
    }

    @Test
    public void getFieldByLong() throws Exception {
        class yolo {
            @Option(longOpt = "take-test", shortOpt = 'n')
            boolean takeTest;
        }

        OptionParser op = new OptionParser(Arrays.stream(new yolo().getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Option.class))
                .toArray(Field[]::new), new String[]{});

        Optional<Field> f = op.getFieldByLong("take-test");
        assertTrue(f.isPresent());
        assertEquals("takeTest", f.get().getName());
        assertEquals(boolean.class, f.get().getType());
    }

}