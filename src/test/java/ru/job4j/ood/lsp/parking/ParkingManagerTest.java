package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParkingManagerTest {

    @Test
    void whenParkOnlyPassengerCars() {
        Parking parking = new CarParking(2, 0);
        ParkingManager manager = new ParkingManager(parking);
        assertThat(manager.manege(new PassengerCar("A123AA00"))).isTrue();
        assertThat(manager.manege(new PassengerCar("T123TT00"))).isTrue();
        assertThat(manager.manege(new PassengerCar("H123HH00"))).isFalse();
    }

    @Test
    void whenParkOnlyTrucks() {
        Parking parking = new CarParking(0, 2);
        ParkingManager manager = new ParkingManager(parking);
        assertThat(manager.manege(new Truck(2, "A123AA00"))).isTrue();
        assertThat(manager.manege(new Truck(2, "T123TT00"))).isTrue();
        assertThat(manager.manege(new Truck(2, "H123HH00"))).isFalse();
    }

    @Test
    void whenParkOnlyTrucksWhichHaveDifferentSize() {
        Parking parking = new CarParking(0, 3);
        ParkingManager manager = new ParkingManager(parking);
        assertThat(manager.manege(new Truck(2, "A123AA00"))).isTrue();
        assertThat(manager.manege(new Truck(3, "T123TT00"))).isTrue();
        assertThat(manager.manege(new Truck(10, "H123HH00"))).isTrue();
    }

    @Test
    void whenParkPassengerCarsAndTruckInSpecialParkingPlace() {
        Parking parking = new CarParking(2, 2);
        ParkingManager manager = new ParkingManager(parking);
        assertThat(manager.manege(new PassengerCar("A123AA00"))).isTrue();
        assertThat(manager.manege(new PassengerCar("T123TT00"))).isTrue();
        assertThat(manager.manege(new Truck(2, "H123HH00"))).isTrue();
        assertThat(manager.manege(new Truck(2, "K123KK00"))).isTrue();
    }

    @Test
    void whenParkPassengerCarsAndTruckInSpecialParkingPlaceAndTrucksHaveDifferentSize() {
        Parking parking = new CarParking(2, 2);
        ParkingManager manager = new ParkingManager(parking);
        assertThat(manager.manege(new PassengerCar("A123AA00"))).isTrue();
        assertThat(manager.manege(new PassengerCar("T123TT00"))).isTrue();
        assertThat(manager.manege(new Truck(3, "H123HH00"))).isTrue();
        assertThat(manager.manege(new Truck(5, "K123KK00"))).isTrue();
    }

    @Test
    void whenParkingHaveNotTruckPlacesOnlyPassengerCarPlaces() {
        Parking parking = new CarParking(5, 0);
        ParkingManager manager = new ParkingManager(parking);
        assertThat(manager.manege(new PassengerCar("A123AA00"))).isTrue();
        assertThat(manager.manege(new PassengerCar("T123TT00"))).isTrue();
        assertThat(manager.manege(new Truck(2, "H123HH00"))).isTrue();
        assertThat(manager.manege(new Truck(2, "K123KK00"))).isFalse();
    }

    @Test
    void whenParkingDifferentCarsAndTrucksPlaceIsGettingFull() {
        Parking parking = new CarParking(5, 3);
        ParkingManager manager = new ParkingManager(parking);
        assertThat(manager.manege(new PassengerCar("A123AA00"))).isTrue();
        assertThat(manager.manege(new PassengerCar("T123TT00"))).isTrue();
        assertThat(manager.manege(new Truck(5, "H123HH00"))).isTrue();
        assertThat(manager.manege(new Truck(2, "K123KK00"))).isTrue();
        assertThat(manager.manege(new Truck(7, "B123BB00"))).isTrue();
        assertThat(manager.manege(new Truck(4, "X123XX00"))).isFalse();
        assertThat(manager.manege(new Truck(3, "M123MM00"))).isTrue();
        assertThat(manager.manege(new PassengerCar("P123PP00"))).isFalse();
        assertThat(manager.manege(new Truck(5, "E123EE00"))).isFalse();
    }
}