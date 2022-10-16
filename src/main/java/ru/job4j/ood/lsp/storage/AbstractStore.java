package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.util.function.DoublePredicate;

import static java.time.temporal.ChronoUnit.DAYS;

public abstract class AbstractStore implements Store {

    protected double getQuality(Food food) {
        long expirationDate = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long daysPassed = DAYS.between(food.getCreateDate(), LocalDate.now());
        return (double) daysPassed * 100 / expirationDate;
    }

    protected boolean checkQuality(DoublePredicate filter, Food food) {
        return filter.test(getQuality(food));
    }
}
