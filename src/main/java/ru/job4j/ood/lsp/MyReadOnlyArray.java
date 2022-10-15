package ru.job4j.ood.lsp;

import java.util.Iterator;

public class MyReadOnlyArray extends MyArray {
    @Override
    public void add(Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return super.iterator();
    }
}
