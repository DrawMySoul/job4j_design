package ru.job4j.ood.lsp.storage;

import java.util.function.DoublePredicate;

public class Warehouse extends AbstractStore {
    private static final DoublePredicate WAREHOUSE_QUALITY = p -> p < 25;

    @Override
    protected boolean check(Food food) {
        return WAREHOUSE_QUALITY.test(QualityUtil.getQuality(food));
    }
}
