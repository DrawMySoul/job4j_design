package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoublePredicate;

public class Trash extends AbstractStore {
    private static final DoublePredicate TRASH_QUALITY = p -> p >= 100;
    private final List<Food> storage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (checkQuality(TRASH_QUALITY, food)) {
            storage.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> getStorage() {
        return List.copyOf(storage);
    }
}
