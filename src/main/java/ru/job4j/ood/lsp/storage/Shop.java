package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoublePredicate;

public class Shop extends AbstractStore {
    private static final DoublePredicate SHOP_QUALITY = p -> p > 25 && p <= 75;
    private static final DoublePredicate SHOP_QUALITY_DISCOUNT = p -> p > 75 && p < 100;
    private final List<Food> storage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (checkQuality(SHOP_QUALITY, food)) {
            storage.add(food);
            result = true;
        } else if (checkQuality(SHOP_QUALITY_DISCOUNT, food)) {
            food.setPriceWithDiscount();
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
