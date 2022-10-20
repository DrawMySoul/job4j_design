package ru.job4j.ood.lsp.storage;

import java.util.function.DoublePredicate;

public class Warehouse extends AbstractStore {
    private static final DoublePredicate WAREHOUSE_QUALITY = p -> p < 25;
    private final QualityUtil foodQuality;

    public Warehouse(QualityUtil quality) {
        this.foodQuality = quality;
    }

    @Override
    protected boolean check(Food food) {
        return WAREHOUSE_QUALITY.test(foodQuality.getQuality(food));
    }
}
