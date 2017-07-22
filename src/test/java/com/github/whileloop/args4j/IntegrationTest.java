package com.github.whileloop.args4j;

import com.github.whileloop.args4j.annotation.Option;
import com.github.whileloop.args4j.annotation.Program;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    @Program(name = "TestClass",
            usage = "[options...] command")
    private static class TestClass {

        @Option(longOpt = "iface", shortOpt = 'i', required = true, desc = "interface to listen on")
        private static String iface = "eth1";

        @Option(longOpt = "outFile", shortOpt = 'o', desc = "log output file")
        private final File outFile = null;

        @Option(longOpt = "timeout", shortOpt = 't', desc = "log output file")
        private int timeout;

        @Option(longOpt = "ports", shortOpt = 'p', desc = "ports")
        private int[] ports;

        @Option(longOpt = "ips", shortOpt = 'a', desc = "ips")
        private List<String> ips;

        @Option(longOpt = "files", shortOpt = 'f', desc = "html files")
        private List<Path> files;
    }

    @Test
    public void testLongOpt() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Parser.parseArgs(TestClass.class, "-i eth2".split(" "));
        assertEquals("eth2", TestClass.iface);

        TestClass t = new TestClass();
        Parser.parseArgs(t, "-o out.log -p 80,93,23 -t 50 -a google.com,facebook.com,anthonyalves.science -f /path/to/a.html,a.html,/no/file.log".split(" "));
        assertEquals("out.log", t.outFile.toString());
        assertEquals("eth2", t.iface);
        assertEquals(50, t.timeout);
        assertArrayEquals(new int[]{80,93,23}, t.ports);
        assertEquals(Arrays.asList("google.com", "facebook.com", "anthonyalves.science"), t.ips);
        assertEquals(Arrays.asList(Paths.get("/path/to/a.html"), Paths.get("a.html"), Paths.get("/no/file.log")), t.files);
    }
}
