package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class PassengerCar implements Car {
    private int size = 1;
    private String licencePlate;

    public PassengerCar(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getLicencePlate() {
        return licencePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PassengerCar that = (PassengerCar) o;
        return size == that.size && Objects.equals(licencePlate, that.licencePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, licencePlate);
    }
}
