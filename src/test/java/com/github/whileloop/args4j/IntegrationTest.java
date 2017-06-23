package com.github.whileloop.args4j;

import com.github.whileloop.args4j.annotation.Option;
import com.github.whileloop.args4j.annotation.Program;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    @Program(name = "TestClass",
            usage = "[options...] command")
    private static class TestClass {

        @Option(longOpt = "iface", shortOpt = 'i', required = true, desc = "interface to listen on")
        private static String iface = "eth1";

        @Option(longOpt = "outFile", shortOpt = 'o', desc = "log output file")
        private File outFile;

        @Option(longOpt = "timeout", shortOpt = 't', desc = "log output file")
        private int timeout;

        @Option(longOpt = "ports", shortOpt = 'p', desc = "ports")
        private int[] ports;
    }

    @Test
    public void testLongOpt() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Parser.parseArgs((Class) TestClass.class, "-i eth2".split(" "));
        assertEquals("eth2", TestClass.iface);

        TestClass t = new TestClass();
        assertEquals("eth2", t.iface);
        Parser.parseArgs(t, "-o out.log -p 80,93,23 -t 50".split(" "));
        assertEquals("out.log", t.outFile.toString());
        assertEquals("eth2", t.iface);
        assertEquals(50, t.timeout);
        assertArrayEquals(new int[]{80,93,23}, t.ports);
    }
}
