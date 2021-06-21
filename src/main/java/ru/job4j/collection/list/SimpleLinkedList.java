package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int length = 0;
    private int modCount = 0;

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
        Node<E> newElement = new Node<>(value, lastNode, null);
        if (firstNode == null) {
            firstNode = newElement;
        } else {
            lastNode.next = newElement;
        }
        lastNode = newElement;
        length++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, length);
        Node<E> foundNode = firstNode;
        int count = 0;
        while (count < index) {
            foundNode = foundNode.next;
            count++;
        }
        return foundNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private Node<E> element = firstNode;
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
                E result = element.item;
                element = element.next;
                point++;
                return result;
            }
        };
    }
}
