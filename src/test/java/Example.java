import com.github.whileloop.args4j.ConvertFactory;
import com.github.whileloop.args4j.Converter;
import com.github.whileloop.args4j.Parser;
import com.github.whileloop.args4j.annotation.Option;
import com.github.whileloop.args4j.annotation.Program;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


@Program(name = "Example",
        usage = "[options...] command")
public class Example {

    @Option(longOpt = "iface", shortOpt = 'i', required = true, desc = "interface to listen on")
    private static String iface = "eth1";

    @Option(longOpt = "outFile", shortOpt = 'o', desc = "log output file")
    private  File outFile;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Example t = new Example();

        Parser.parseArgs(Example.class, args); // static fields
        t.run(args); // non-static fields

        System.out.println(iface);
        System.out.println(t.outFile);

    }

    private void run(String[] args) {
        Parser p = new Parser.Builder(this)
                .addConverters(new Converter<Map>() {
                    /**
                     * EX: "MODEL:mazda,MAKE:mazda3,YEAR:2013"
                     * @param value csv of colon sep values
                     * @return
                     */
                    @Override
                    public Map<String, Object> convert(ConvertFactory factory, Type type, String value) {
                        return Arrays.stream(value.split(","))
                                .map(s -> s.split(":"))
                                .collect(Collectors.toMap(e -> e[0], e -> e[1]));
                    }

                    @Override
                    public Class[] getType() {
                        return new Class[]{Map.class};
                    }
                })
                .build();
        p.parse(args);

        System.out.println(this.iface);
        System.out.println(this.outFile);
    }
}