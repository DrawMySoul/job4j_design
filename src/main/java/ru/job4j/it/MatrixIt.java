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
		boolean result = row < data[column].length;
		if (!result) {
			row = 0;
			for (int i = column + 1; i < data.length; i++) {
				if (row < data[i].length) {
					result = true;
					column = i;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return data[column][row++];
	}
}
