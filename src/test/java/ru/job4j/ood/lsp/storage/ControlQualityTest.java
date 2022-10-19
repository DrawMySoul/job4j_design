package ru.job4j.ood.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
                LocalDate.now().minusDays(4),
                LocalDate.now().plusDays(13),
                10
            ),
            new Food("meat",
                LocalDate.now().minusDays(4),
                LocalDate.now().plusDays(4),
                500
            ),
            new Food("chicken",
                LocalDate.now().minusDays(4),
                LocalDate.now().plusDays(1),
                100
            ),
            new Food("banana",
                LocalDate.now().minusDays(4),
                LocalDate.now().minusDays(4),
                60
            )
        );
        foods.forEach(controller::control);
        assertThat(shop.getFoods()).isNotNull()
            .hasSize(2)
            .contains(foods.get(1), foods.get(2))
            .doesNotContain(foods.get(0), foods.get(3));
        assertThat(warehouse.getFoods()).isNotNull()
            .hasSize(1)
            .contains(foods.get(0))
            .doesNotContain(foods.get(1), foods.get(2), foods.get(3));
        assertThat(trash.getFoods()).isNotNull()
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
            LocalDate.now().minusDays(4),
            LocalDate.now().plusDays(1),
            price);
        int expected = (int) (price * food.getDiscount());
        controller.control(food);
        assertThat(shop.getFoods()).isNotNull()
            .hasSize(1)
            .contains(food);
        assertThat(warehouse.getFoods()).isNullOrEmpty();
        assertThat(trash.getFoods()).isNullOrEmpty();
        assertThat(expected).isEqualTo(food.getPrice());
    }
}