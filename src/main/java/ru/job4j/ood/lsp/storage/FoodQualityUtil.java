package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class FoodQualityUtil implements QualityUtil {

    @Override
    public double getQuality(Food food) {
        long expirationDate = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long daysPassed = DAYS.between(food.getCreateDate(), LocalDate.now());
        return (double) daysPassed * 100 / expirationDate;
    }
}
