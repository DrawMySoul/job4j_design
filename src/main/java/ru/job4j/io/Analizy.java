package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (
                BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            int status = 1;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (status == 1 && (line.contains("500") || line.contains("400"))) {
                    out.print(line.split("\\s")[1] + ";");
                    status = 0;
                } else if (status == 0 && line.contains("200")) {
                    out.println(line.split("\\s")[1]);
                    status = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
