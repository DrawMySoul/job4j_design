package ru.job4j.ood.lsp.storage;

import java.util.function.DoublePredicate;

public class Shop extends AbstractStore {
    private static final DoublePredicate SHOP_QUALITY = p -> p > 25 && p <= 75;
    private static final DoublePredicate SHOP_QUALITY_DISCOUNT = p -> p > 75 && p < 100;

    @Override
    protected boolean check(Food food) {
        boolean result = false;
        double quality = QualityUtil.getQuality(food);
        if (SHOP_QUALITY_DISCOUNT.test(quality)) {
            food.setPriceWithDiscount();
            result = true;
        } else if (SHOP_QUALITY.test(quality)) {
            result = true;
        }
        return result;
    }
}
