package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> storage = new ArrayList<>();

    protected abstract boolean check(Food food);

    public boolean add(Food food) {
        boolean result = false;
        if (check(food)) {
            storage.add(food);
            result = true;
        }
        return result;
    }

    public List<Food> getFoods() {
        return List.copyOf(storage);
    }
}
