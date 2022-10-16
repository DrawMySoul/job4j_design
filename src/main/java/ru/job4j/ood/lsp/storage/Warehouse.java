package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoublePredicate;

public class Warehouse extends AbstractStore {
    private static final DoublePredicate WAREHOUSE_QUALITY = p -> p < 25;
    private final List<Food> storage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (checkQuality(WAREHOUSE_QUALITY, food)) {
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
