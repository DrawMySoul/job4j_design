package ru.job4j.ood.lsp.storage;

import java.util.function.DoublePredicate;

public class Trash extends AbstractStore {
    private static final DoublePredicate TRASH_QUALITY = p -> p >= 100;
    private final QualityUtil foodQuality;

    public Trash(QualityUtil quality) {
        this.foodQuality = quality;
    }

    @Override
    protected boolean check(Food food) {
        return TRASH_QUALITY.test(foodQuality.getQuality(food));
    }
}
