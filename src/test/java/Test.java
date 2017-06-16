import com.github.whileloop.args4j.Parser;
import com.github.whileloop.args4j.annotation.Option;

import java.io.File;

public class Test {

    @Option(longOpt = "iface", shortOpt = "i", required = true)
    private String iface = "eth1";

    @Option(longOpt = "outFile", shortOpt = "o")
    private File outFile;

    public static void main(String[] args) {
        new Test().run(args);

    }

    private void run(String[] args){
        Parser p = new Parser.Builder(this)
                .addConverters()
                .build();
        p.parse(args);
        Parser.parseArgs(this, args);
        System.out.println(this.iface);
        System.out.println(this.outFile);
    }
}
