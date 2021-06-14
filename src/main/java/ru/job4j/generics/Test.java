package ru.job4j.generics;

public class Test {
	public static void main(String[] args) {
		SimpleArray<Integer> arr = new SimpleArray<>(5);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(5);
		System.out.println(arr.get(5));
	}
}
