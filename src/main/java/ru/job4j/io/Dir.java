package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getName() + " " + subfile.length());
        }
    }
}
