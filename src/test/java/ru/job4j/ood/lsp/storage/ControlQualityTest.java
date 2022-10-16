package ru.job4j.ood.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenControl() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controller = new ControlQuality(List.of(shop, warehouse, trash));
        List<Food> foods = List.of(
            new Food("bread",
                LocalDate.of(2022, Month.OCTOBER, 12),
                LocalDate.of(2022, Month.OCTOBER, 29),
                10
            ),
            new Food("meat",
                LocalDate.of(2022, Month.OCTOBER, 12),
                LocalDate.of(2022, Month.OCTOBER, 20),
                500
            ),
            new Food("chicken",
                LocalDate.of(2022, Month.OCTOBER, 12),
                LocalDate.of(2022, Month.OCTOBER, 17),
                100
            ),
            new Food("banana",
                LocalDate.of(2022, Month.OCTOBER, 12),
                LocalDate.of(2022, Month.OCTOBER, 12),
                60
            )
        );
        foods.forEach(controller::control);
        assertThat(shop.getStorage()).isNotNull()
            .hasSize(2)
            .contains(foods.get(1), foods.get(2))
            .doesNotContain(foods.get(0), foods.get(3));
        assertThat(warehouse.getStorage()).isNotNull()
            .hasSize(1)
            .contains(foods.get(0))
            .doesNotContain(foods.get(1), foods.get(2), foods.get(3));
        assertThat(trash.getStorage()).isNotNull()
            .hasSize(1)
            .contains(foods.get(3))
            .doesNotContain(foods.get(0), foods.get(1), foods.get(2));
    }

    @Test
    void whenQualityLess75AndMore100ThenFoodShouldBeInShopAndChangePrice() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        ControlQuality controller = new ControlQuality(List.of(shop, warehouse, trash));
        int price = 1000;
        Food food = new Food("food",
            LocalDate.of(2022, Month.OCTOBER, 12),
            LocalDate.of(2022, Month.OCTOBER, 17),
            price);
        int expected = (int) (price * food.getDiscount());
        controller.control(food);
        assertThat(shop.getStorage()).isNotNull()
            .hasSize(1)
            .contains(food);
        assertThat(warehouse.getStorage()).isNullOrEmpty();
        assertThat(trash.getStorage()).isNullOrEmpty();
        assertThat(expected).isEqualTo(food.getPrice());
    }
}