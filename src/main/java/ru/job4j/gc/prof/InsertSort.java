package ru.job4j.gc.prof;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class InsertSort implements Sort {
    @Override
    public boolean sort(Data data) {
        int[] array = data.getClone();
        LocalDateTime before = LocalDateTime.now();
        sort(array);
        System.out.println(ChronoUnit.MILLIS.between(before, LocalDateTime.now()));
        return true;
    }

    private void sort(int[] array) {
        int in, out;
        for (out = 1; out < array.length; out++) {
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
    }
}
