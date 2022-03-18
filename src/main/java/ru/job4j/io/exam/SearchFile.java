package ru.job4j.io.exam;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFile extends SimpleFileVisitor<Path> {
    Predicate<Path> condition;
    List<Path> files = new ArrayList<>();

    public SearchFile(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            files.add(file.getFileName());
        }
        return CONTINUE;
    }

    public List<Path> getFiles() {
        return files;
    }
}
