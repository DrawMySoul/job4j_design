package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
	private T[] container;
	private int modCount = 0;
	private int length = 0;

	public SimpleArray() {
		this.container = (T[]) new Object[5];
	}

	public void add(T model) {
		if (length == container.length) {
			container = Arrays.copyOf(container, length + 5);
		}
		container[length++] = model;
		modCount++;
	}

	public T get(int index) {
		checkIndex(index, this.length);
		return container[index];
	}

	public void set(int index, T model) {
		checkIndex(index, this.length);
		container[index] = model;
		modCount++;
	}

	public void remove(int index) {
		checkIndex(index, this.length);
		System.arraycopy(container, index + 1, container, index, length - index - 1);
		container[length - 1] = null;
		length--;
		modCount++;
	}

	public int checkIndex(int index, int length) {
		if (0 <= index && index < length) {
			return index;
		} else {
			throw new IndexOutOfBoundsException();
		}
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
