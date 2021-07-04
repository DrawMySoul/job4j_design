package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenPutTrue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "one"));
    }

    @Test
    public void whenPutFalse() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        assertFalse(simpleMap.put(1, "one"));
    }

    @Test
    public void putMoreThenEight() {
        String[] numbers = {"one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"};
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        for (int i = 0; i < numbers.length; i++) {
            simpleMap.put(i, numbers[i]);
        }
        assertThat(simpleMap.get(8), is(numbers[8]));
    }

    @Test
    public void getValue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        assertThat(simpleMap.get(1), is("one"));
    }

    @Test
    public void getNull() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertNull(simpleMap.get(1));
    }

    @Test
    public void removeTrue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        assertTrue(simpleMap.remove(1));
        assertNull(simpleMap.get(1));
    }

    @Test
    public void removeFalse() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertFalse(simpleMap.remove(19));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        Iterator<Integer> it = simpleMap.iterator();
        simpleMap.put(2, "two");
        it.next();
    }
}