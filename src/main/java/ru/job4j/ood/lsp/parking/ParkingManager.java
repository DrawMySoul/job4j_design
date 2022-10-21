package ru.job4j.ood.lsp.parking;

public class ParkingManager {
    private Parking parking;

    public ParkingManager(Parking parking) {
        this.parking = parking;
    }

    public boolean manege(Car car) {
        return parking.parkCar(car);
    }
}
