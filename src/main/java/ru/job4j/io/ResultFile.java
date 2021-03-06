package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    out.write(String.format("%d * %d = %d%n", i, j, i * j).getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
