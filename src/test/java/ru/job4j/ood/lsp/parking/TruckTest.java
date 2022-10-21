package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TruckTest {

    @Test
    void whenValidTruckSize() {
        Car truck = new Truck(2, "A123AA00");
        Car bigTruck = new Truck(10, "T123TT00");
        assertThat(truck.getSize()).isEqualTo(2);
        assertThat(bigTruck.getSize()).isEqualTo(10);
    }

    @Test
    void whenInvalidTruckSize() {
        assertThatThrownBy(() -> new Truck(1, "A123AA00"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid Truck size");
        assertThatThrownBy(() -> new Truck(0, "T123TT00"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid Truck size");
        assertThatThrownBy(() -> new Truck(-10, "H123HH00"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid Truck size");
    }
}