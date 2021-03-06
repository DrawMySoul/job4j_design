package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
	private T[] array;
	private int length = 0;

	public SimpleArray(int size) {
		this.array = (T[]) new Object[size];
	}

	public void add(T model) {
		array[length++] = model;
	}

	public T get(int index) {
		checkIndex(index, this.length);
		return array[index];
	}

	public void set(int index, T model) {
		checkIndex(index, this.length);
		array[index] = model;
	}

	public void remove(int index) {
		checkIndex(index, this.length);
		System.arraycopy(array, index + 1, array, index, length - index - 1);
		array[length - 1] = null;
		length--;
	}

	public int checkIndex(int index, int length) {
		if (index < 0 || index >= length) {
			throw new IndexOutOfBoundsException();
		}
		return index;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int point = 0;

			@Override
			public boolean hasNext() {
				return point < length;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return array[point++];
			}
		};
	}
}
