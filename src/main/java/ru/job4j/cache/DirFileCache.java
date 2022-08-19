package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder value = new StringBuilder();
        String filePath = String.join("/", cachingDir, key).replaceAll("/./", "/");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines().forEach(value::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value.toString();
    }
}
