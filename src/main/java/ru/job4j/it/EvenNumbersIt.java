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
		if (pointer == data.length) {
			return false;
		} else if (data[pointer] % 2 != 0) {
			return findEvenNumbers();
		} else {
			return true;
		}
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return data[pointer++];
	}

	public boolean findEvenNumbers() {
		for (; pointer < data.length; pointer++) {
			if (data[pointer] % 2 == 0) {
				return true;
			}
		}
		return false;
	}
}
