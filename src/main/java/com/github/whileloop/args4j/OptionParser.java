package com.github.whileloop.args4j;


import com.github.whileloop.args4j.annotation.Option;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class OptionParser {
    static final String SHORT_SEP = "-";
    static final String LONG_SEP = "--";
    private final Field[] _fields;
    private final String[] _args;

    OptionParser(Field[] fields, String[] args) {
        this._fields = fields;
        this._args = args;
    }

    Map<String, String> parseShortOpts() {
        Map<String, String> opts = new HashMap<>();
        for (int i = 0; i < _args.length; i++) {
            if (!_args[i].startsWith(SHORT_SEP) || _args[i].startsWith(LONG_SEP)) {
                continue;
            }

            if (_args[i].length() > 2) {
                // multi flag opts
                for (int j = 1; j < _args[i].length(); j++) {
                    char opt = _args[i].charAt(j);
                    Optional<Field> f = getFieldByShort(String.valueOf(opt));
                    if (!f.isPresent()) {
                        System.err.printf("Unknown option %s%n", opt);
                    } else {
                        opts.put(String.valueOf(opt), null);
                    }
                }
            } else if (_args[i].length() == 2 && (i + 1 < _args.length) && !_args[i + 1].startsWith(SHORT_SEP)) {
                // single option with maybe field
                // it's a flag if the wanting field is a boolean
                char opt = _args[i].charAt(1);
                Optional<Field> f = getFieldByShort(String.valueOf(opt));
                if (!f.isPresent()) {
                    System.err.printf("Unknown option %s%n", opt);
                } else {
                    if (f.get().getType() == Boolean.class || f.get().getType() == boolean.class) {
                        // just a short flag.. next arg is a program arg
                        opts.put(String.valueOf(opt), null);
                    }
                }
            } else if (_args[i].length() == 2 && (i + 1 < _args.length) && _args[i + 1].startsWith(SHORT_SEP)) {
                // single flag with a flag as next arg
                char opt = _args[i].charAt(1);
                Optional<Field> f = getFieldByShort(String.valueOf(opt));
                if (!f.isPresent()) {
                    System.err.printf("Unknown option %s%n", opt);
                } else {
                    opts.put(String.valueOf(opt), null);
                }
            } else {
                // single flag with no more params left
                char opt = _args[i].charAt(1);
                Optional<Field> f = getFieldByShort(String.valueOf(opt));
                if (!f.isPresent()) {
                    System.err.printf("Unknown option %s%n", opt);
                } else {
                    opts.put(String.valueOf(opt), null);
                }
            }
        }
        return opts;
    }

    Map<String, String> parseLongOpts() {
        Map<String, String> opts = new HashMap<>();
        for (int i = 0; i < _args.length; i++) {
            if (!_args[i].startsWith(LONG_SEP)) {
                continue;
            }

            if ((i + 1 < _args.length) && _args[i + 1].startsWith(LONG_SEP)) {
                // long opt flag
                opts.put(_args[i], null);
            } else if (_args[i].contains("=")) {
                // long opt with value
                String[] vals = _args[i].split("=", 1);
                opts.put(vals[0].replace(LONG_SEP, ""), vals[1]);
            } else if ((i + 1 < _args.length) && !_args[i + 1].startsWith(LONG_SEP)) {
                // single option with maybe field
                String opt = _args[i].replace(LONG_SEP, "");
                Optional<Field> f = getFieldByLong(opt);
                if (!f.isPresent()) {
                    System.err.printf("Unknown option %s%n", opt);
                } else {
                    if (f.get().getType() == Boolean.class || f.get().getType() == boolean.class) {
                        // just a long flag.. next arg is a program arg
                        opts.put(String.valueOf(opt), null);
                    }
                }
            }
        }
        return opts;
    }

    Optional<Field> getFieldByShort(String s) {
        return Arrays.stream(_fields)
                .filter((Field f) -> f.getAnnotation(Option.class).shortOpt().equals(s))
                .findFirst();
    }

    Optional<Field> getFieldByLong(String s) {
        return Arrays.stream(_fields)
                .filter((Field f) -> f.getAnnotation(Option.class).longOpt().equals(s))
                .findFirst();
    }
}
