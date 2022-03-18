package ru.job4j.io.exam;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("All arguments are absent. Use java -jar target/searchFilesByCriteria.jar -d=START_DIRECTORY -n=FILE_NAME -t=SEARCH_TYPE -o=WRITE_RESULT");
        } else if (args.length == 1) {
            throw new IllegalArgumentException("One or more arguments are absent. Use java -jar target/searchFilesByCriteria.jar -d=START_DIRECTORY -n=FILE_NAME -t=SEARCH_TYPE -o=WRITE_RESULT");
        }

        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            int split = arg.indexOf("=");
            if (split == arg.length() - 1) {
                throw new IllegalArgumentException("Parameter format is wrong. Should be -SOME_PARAMETER=VALUE");
            }

            values.put(
                arg.substring(0, split).replace("-", ""),
                arg.substring(split).replaceFirst("=", "")
            );
        }
    }
}
