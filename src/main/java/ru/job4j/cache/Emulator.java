package ru.job4j.cache;

import java.io.File;
import java.util.Scanner;

public class Emulator {
    private static final String MENU = """
        =======================================
                       MENU
        Select 1 to change cached directory.
        Select 2 to load file content into cache.
        Select 3 to get file content from cache.
        Select 4 to exit.
        """;
    private static final String SELECT = "Choose the menu: ";
    private static final String FILE_NAME = "Enter file name: ";
    private static final String DIR_NAME = "Enter directory: ";
    private static final String WRONG_DIR = "Wrong directory, please try again!";
    private static final String WRONG_FILE = "File not found!";
    private static final String EXIT = "The end of work";

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AbstractCache<String, String> cache = getDirectory();
        boolean isContinue = true;
        while (isContinue) {
            System.out.print(MENU);
            System.out.print(SELECT);
            int userChoice = scanner.nextInt();
            if (userChoice == 1) {
                cache = getDirectory();
            } else if (userChoice == 2) {
                System.out.print(FILE_NAME);
                String fileName = scanner.next();
                cache.put(fileName, cache.get(fileName));
            } else if (userChoice == 3) {
                System.out.print(FILE_NAME);
                String value = cache.get(scanner.next());
                System.out.println(value != null ? value : WRONG_FILE);
            } else if (userChoice == 4) {
                isContinue = false;
                System.out.println(EXIT);
            }
        }
    }

    private static AbstractCache<String, String> getDirectory() {
        String dir = null;
        boolean isCorrectPath = false;
        while (!isCorrectPath) {
            System.out.println(DIR_NAME);
            dir = scanner.next();
            if (chekDir(dir)) {
                isCorrectPath = true;
            } else {
                System.out.println(WRONG_DIR);
            }
        }
        return new DirFileCache(dir);
    }

    private static boolean chekDir(String path) {
        File file = new File(path);
        boolean result = false;
        if (file.exists() && file.isDirectory()) {
            result = true;
        }
        return result;
    }
}
