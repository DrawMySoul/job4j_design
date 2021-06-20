package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int length = 0;
    private int modCount = 0;
    private Node<E>[] container;

    public SimpleLinkedList() {
        container = new Node[5];
    }

    private class Node<E> {
        private E item;
        private Node<E> prev;
        private Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        checkLength();
        if (length == 0) {
            firstNode = new Node<>(value, null, lastNode);
            lastNode = firstNode;
        } else {
            lastNode = new Node<>(value, lastNode, null);
            container[length - 1].next = lastNode;
        }
        container[length++] = lastNode;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, length);
        return container[index].item;
    }

    public void checkLength() {
        if (length == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return point < length;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++].item;
            }
        };
    }
}
