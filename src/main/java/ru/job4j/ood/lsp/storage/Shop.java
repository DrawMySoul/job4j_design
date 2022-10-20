package ru.job4j.ood.lsp.storage;

import java.util.function.DoublePredicate;

public class Shop extends AbstractStore {
    private static final DoublePredicate SHOP_QUALITY = p -> p > 25 && p <= 75;
    private static final DoublePredicate SHOP_QUALITY_DISCOUNT = p -> p > 75 && p < 100;
    private final QualityUtil foodQuality;

    public Shop(QualityUtil quality) {
        this.foodQuality = quality;
    }

    @Override
    protected boolean check(Food food) {
        boolean result = false;
        double quality = foodQuality.getQuality(food);
        if (SHOP_QUALITY_DISCOUNT.test(quality)) {
            food.setPrice((int) (food.getPrice() * food.getDiscount()));
            result = true;
        } else if (SHOP_QUALITY.test(quality)) {
            result = true;
        }
        return result;
    }
}
