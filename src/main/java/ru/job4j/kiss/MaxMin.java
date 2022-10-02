package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findByCondition(value, (x, y) -> comparator.compare(x, y) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findByCondition(value, (x, y) -> comparator.compare(x, y) < 0);
    }

    private static <T> T findByCondition(List<T> value, BiPredicate<T, T> condition) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T result = value.get(0);
        for (T v : value) {
            if (condition.test(v, result)) {
                result = v;
            }
        }
        return result;
    }
}
