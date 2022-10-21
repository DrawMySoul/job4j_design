package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Truck implements Car {
    private int size;
    private String licencePlate;

    public Truck(int size, String licencePlate) {
        if (size <= 1) {
            throw new IllegalArgumentException("Invalid Truck size. Size should be more than 1");
        }
        this.size = size;
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
        Truck truck = (Truck) o;
        return size == truck.size && Objects.equals(licencePlate, truck.licencePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, licencePlate);
    }
}
