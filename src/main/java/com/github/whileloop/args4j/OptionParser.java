package com.github.whileloop.args4j;


import java.util.Map;

class OptionParser {
    static final String SHORT_SEP = "-";
    static final String LONG_SEP = "--";

    public static Map<String, String> parseShortOpts(String[] args) {
        return parseOpts(args, SHORT_SEP);
    }

    static Map<String, String> parseLongOpts(String[] args) {
        return parseOpts(args, LONG_SEP);
    }

    private static Map<String, String> parseOpts(String[] args, String sep) {
        return null;
    }
}
