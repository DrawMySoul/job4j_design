package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(Food food) {
        stores.forEach(s -> s.add(food));
    }

    public void resort() {
        List<Food> resortFoods = new ArrayList<>();
        for (Store store : stores) {
            resortFoods.addAll(store.getFoods());
            store.removeFoods();
        }
        resortFoods.forEach(this::control);
    }
}
