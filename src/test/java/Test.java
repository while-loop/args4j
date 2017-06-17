import com.github.whileloop.args4j.Parser;
import com.github.whileloop.args4j.annotation.Option;
import com.github.whileloop.args4j.annotation.Program;

import java.io.File;

@Program(name = "Test",
        usage = "[options...] command")
public class Test {

    @Option(longOpt = "iface", shortOpt = "i", required = true, desc = "interface to listen on")
    private String iface = "eth1";

    @Option(longOpt = "outFile", shortOpt = "o", desc = "log output file")
    private File outFile;

    public static void main(String[] args) {
        new Test().run(args);

    }

    private void run(String[] args) {
        Parser p = new Parser.Builder(this)
                .addConverters()
                .build();
        p.parse(args);
        Parser.parseArgs(this, args);
        System.out.println(this.iface);
        System.out.println(this.outFile);
    }
}
