package ru.job4j.ood;

public class Contact {
    private static final String NUMBER_REGEX = "^[+]?[(]?[0-9]{3}[)]?[-\s.]?[0-9]{3}[-\s.]?[0-9]{4,6}$";
    String name;
    String number;

    public Contact(String name, String number) {
        this.name = name;
        validate(number);
        this.number = number;
    }

    private void validate(String number) {
        if (!number.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException();
        }
    }
}
