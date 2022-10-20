package ru.job4j.ood.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenControl() {
        QualityUtil foodQuality = new FoodQualityUtil();
        Store shop = new Shop(foodQuality);
        Store warehouse = new Warehouse(foodQuality);
        Store trash = new Trash(foodQuality);
        ControlQuality controller = new ControlQuality(List.of(shop, warehouse, trash));
        Food bread = new Food("bread",
            LocalDate.now().minusDays(4),
            LocalDate.now().plusDays(13),
            10
        );
        Food meat = new Food("meat",
            LocalDate.now().minusDays(4),
            LocalDate.now().plusDays(4),
            500
        );
        Food chicken = new Food("chicken",
            LocalDate.now().minusDays(4),
            LocalDate.now().plusDays(1),
            100
        );
        Food banana = new Food("banana",
            LocalDate.now().minusDays(4),
            LocalDate.now().minusDays(4),
            60
        );
        List<Food> foods = List.of(bread, meat, chicken, banana);
        foods.forEach(controller::control);
        assertThat(shop.getFoods()).isNotNull()
            .hasSize(2)
            .contains(meat, chicken)
            .doesNotContain(bread, banana);
        assertThat(warehouse.getFoods()).isNotNull()
            .hasSize(1)
            .contains(bread)
            .doesNotContain(meat, chicken, banana);
        assertThat(trash.getFoods()).isNotNull()
            .hasSize(1)
            .contains(banana)
            .doesNotContain(bread, meat, chicken);
    }

    @Test
    void whenQualityLess75AndMore100ThenFoodShouldBeInShopAndChangePrice() {
        QualityUtil foodQuality = new FoodQualityUtil();
        Store shop = new Shop(foodQuality);
        Store warehouse = new Warehouse(foodQuality);
        Store trash = new Trash(foodQuality);
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