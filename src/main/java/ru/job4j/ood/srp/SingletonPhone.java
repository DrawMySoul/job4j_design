package ru.job4j.ood.srp;

import ru.job4j.ood.Phone;

public class SingletonPhone {
    private static Phone instance;

    private SingletonPhone() {
    }

    public static Phone getInstance() {
        if (instance == null) {
            instance = new Phone();
        }
        return instance;
    }
}
