package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIt implements Iterator<Integer> {
	private int pointer = 0;
	private int[] data;

	public EvenNumbersIt(int[] data) {
		this.data = data;
	}

	@Override
	public boolean hasNext() {
		while (pointer < data.length && data[pointer] % 2 != 0) {
			pointer++;
		}
		return pointer < data.length;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return data[pointer++];
	}
}
