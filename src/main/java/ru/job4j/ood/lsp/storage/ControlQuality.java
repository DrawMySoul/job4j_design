package ru.job4j.ood.lsp.storage;

import java.util.Collection;
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
        stores.stream()
            .map(Store::getFoods)
            .flatMap(Collection::stream)
            .forEach(this::control);
    }
}
