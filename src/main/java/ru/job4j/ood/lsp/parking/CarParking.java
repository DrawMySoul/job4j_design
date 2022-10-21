package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private static final int DEFAULT_CAR_SIZE = 1;
    private final int passengerCarsLimit;
    private final int trucksLimit;
    private final List<Car> passengerCars = new ArrayList<>();
    private final List<Car> trucks = new ArrayList<>();

    public CarParking(int passengerCarsLimit, int trucksLimit) {
        this.passengerCarsLimit = passengerCarsLimit;
        this.trucksLimit = trucksLimit;
    }

    @Override
    public boolean parkCar(Car car) {
        boolean result = false;
        if (car.getSize() == DEFAULT_CAR_SIZE && passengerCars.size() < passengerCarsLimit) {
            passengerCars.add(car);
            result = true;
        } else if (car.getSize() > DEFAULT_CAR_SIZE && trucks.size() < trucksLimit) {
            trucks.add(car);
            result = true;
        } else if (
            car.getSize() > DEFAULT_CAR_SIZE
                && trucks.size() >= trucksLimit
                && passengerCarsLimit - passengerCars.size() >= car.getSize()
        ) {
            for (int i = 0; i < car.getSize(); i++) {
                passengerCars.add(car);
            }
            result = true;
        }
        return result;
    }
}
