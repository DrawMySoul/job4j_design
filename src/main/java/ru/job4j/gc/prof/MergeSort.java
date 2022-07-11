package ru.job4j.gc.prof;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MergeSort implements Sort {

    @Override
    public boolean sort(Data data) {
        int[] array = data.getClone();
        LocalDateTime before = LocalDateTime.now();
        sortMerge(array);
        System.out.println(ChronoUnit.MILLIS.between(before, LocalDateTime.now()));
        return true;
    }

    private int[] sortMerge(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private int[] sort(int[] array, int from, int to) {

        if (from == to) {
            return new int[]{array[from]};
        }
        int mid = (from + to) / 2;
        return merge(
            sort(array, from, mid),
            sort(array, mid + 1, to)
        );
    }

    private int[] merge(int[] left, int[] right) {
        int li = 0;
        int ri = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI != result.length) {
            if (li == left.length) {
                result[resI++] = right[ri++];
            } else if (ri == right.length) {
                result[resI++] = left[li++];
            } else if (left[li] <= right[ri]) {
                result[resI++] = left[li++];
            } else {
                result[resI++] = right[ri++];
            }
        }
        return result;
    }
}
