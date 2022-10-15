package ru.job4j.ood.lsp;

import java.util.Iterator;

public class MyArray<T> implements Iterable<T> {
    private T container[];

    public MyArray() {
        this.container = (T[]) new Object[5];
    }

    public void add(T element) {
    }

    public T get() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
