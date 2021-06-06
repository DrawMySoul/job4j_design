package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
	private final int[][] data;
	private int row = 0;
	private int column = 0;

	public MatrixIt(int[][] data) {
		this.data = data;
	}

	@Override
	public boolean hasNext() {
		if (column == data.length) {
			return false;
		} else if (row == data[column].length) {
			return findNotNullArray();
		} else {
			return true;
		}
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return data[column][row++];
	}

	public boolean findNotNullArray() {
		boolean found = false;
		row = 0;
		for (int i = column + 1; i < data.length; i++) {
			if (row < data[i].length) {
				column = i;
				found = true;
				break;
			}
		}
		return found;
	}
}
