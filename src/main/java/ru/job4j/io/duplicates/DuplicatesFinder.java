package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesFinder {
	public static void main(String[] args) throws IOException {
		Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
	}
}
