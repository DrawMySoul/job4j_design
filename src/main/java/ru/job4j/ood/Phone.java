package ru.job4j.ood;

import java.util.*;

public class Phone {
    List<Contact> contacts = new ArrayList<>();

    public void call() {
        System.out.println();
    }

    public void addContact(String name, String number) {
        contacts.add(new Contact(name, number));
    }
}
