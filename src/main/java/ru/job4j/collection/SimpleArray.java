package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
	private T[] container;
	private int modCount = 0;
	private int length = 0;

	public SimpleArray() {
		this.container = (T[]) new Object[5];
	}

	public void add(T model) {
		if (length == container.length) {
			container = Arrays.copyOf(container, length * 2);
		}
		container[length++] = model;
		modCount++;
	}

	public T get(int index) {
		Objects.checkIndex(index, this.length);
		return container[index];
	}

	public void set(int index, T model) {
		Objects.checkIndex(index, this.length);
		container[index] = model;
		modCount++;
	}

	public void remove(int index) {
		Objects.checkIndex(index, length);
		System.arraycopy(container, index + 1, container, index, length - index - 1);
		container[length - 1] = null;
		length--;
		modCount++;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
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
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return container[point++];
			}
		};
	}
}
