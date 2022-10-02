package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {
    private MaxMin maxMin;

    @Before
    public void init() {
        maxMin = new MaxMin();
    }

    @Test
    public void whenMax() {
        assertEquals(
            Integer.valueOf(94),
            maxMin.max(List.of(78, 22, 64, 55, 94, 52, 22, 89, 18, 39), Integer::compareTo)
        );

        assertEquals(
            "tubby",
            maxMin.max(List.of("donut", "shorts", "tubby", "cold wind"), String::compareTo)
        );
    }

    @Test
    public void whenMin() {
        assertEquals(
            Integer.valueOf(18),
            maxMin.min(List.of(78, 22, 64, 55, 94, 52, 22, 89, 18, 39), Integer::compareTo)
        );

        assertEquals(
            "cold wind",
            maxMin.min(List.of("donut", "shorts", "tubby", "cold wind"), String::compareTo)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenListIsNullThenMustGetException() {
        maxMin.max(null, Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenListHasSizeZeroThenMustGetException() {
        maxMin.min(new ArrayList<>(), Integer::compareTo);
    }
}