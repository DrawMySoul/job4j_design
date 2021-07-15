package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                text.append((char) read);
            }

            String[] numbers = text.toString().split(System.lineSeparator());
            for (String number : numbers) {
                System.out.println(
                        number + (Integer.parseInt(number) % 2 == 0 ? " is even" : " is odd")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
