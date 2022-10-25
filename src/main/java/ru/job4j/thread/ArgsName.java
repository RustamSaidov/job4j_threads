package ru.job4j.thread;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Parameter with key " + key + " does not exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) {

        for (int i = 0; i < args.length; i++) {
            argumentValidation(args[i]);
            String substr1 = args[i].substring(1, args[i].indexOf('='));
            String substr2 = args[i].substring(args[i].indexOf('=') + 1);
            values.put(substr1, substr2);
        }
    }

    private static void argumentValidation(String argsStr) {
        if (!argsStr.startsWith("-")) {
            throw new IllegalArgumentException("The '-' sign of the beginning of the \"-key=value\" type argument was not found");
        }
        if (!argsStr.contains("=")) {
            throw new IllegalArgumentException("The '=' sign between \"-key=value\" type argument was not found");
        }
        if (argsStr.indexOf('=') == 1) {
            throw new IllegalArgumentException("The \"key\" type argument was not found");
        }

        if (argsStr.indexOf('=') == argsStr.length() - 1) {
            throw new IllegalArgumentException("The \"value\" type argument was not found");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The passed array of parameters is empty");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public Map<String, String> getValues() {
        return values;
    }
}
