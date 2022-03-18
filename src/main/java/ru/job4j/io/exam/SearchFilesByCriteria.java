package ru.job4j.io.exam;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchFilesByCriteria {
    public static void main(String[] args) {
        handle(ArgsName.of(args));
    }

    public static void handle(ArgsName argsName) {
        SearchFilesByCriteria searchFiles = new SearchFilesByCriteria();
        List<Path> files = searchFiles.search(Paths.get(argsName.get("d")), argsName.get("t"), argsName.get("n"));
        searchFiles.write(files, argsName.get("o"));

    }

    private List<Path> search(Path path, String searchType, String fileName) {
        SearchFile searchFile = new SearchFile(getCondition(searchType, fileName));
        try {
            Files.walkFileTree(path, searchFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchFile.getFiles();
    }

    private Predicate<Path> getCondition(String searchType, String fileName) {
        return switch (searchType) {
            case "mask" -> path -> FileSystems.getDefault()
                .getPathMatcher("glob:" + fileName)
                .matches(path.getFileName());
            case "name" -> p -> p.toFile().getName().equals(fileName);
            case "regex" -> p -> p.toFile().getName().matches(fileName);
            default -> throw new IllegalArgumentException("Wrong search type! Please, use one of these options : mask, name, regex");
        };
    }

    private void write(List<Path> files, String out) {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            files.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
