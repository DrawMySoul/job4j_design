package ru.job4j.collection.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }


    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2000, Calendar.DECEMBER, 21);
        User user = new User("Mike", 1, calendar);
        User user1 = new User("Mike", 1, calendar);
        User user2 = new User("Mike", 1, calendar);

        /*System.out.println(user.hashCode());
        System.out.println(Objects.hash(user) & (16 - 1));

        System.out.println(user1.hashCode());
        System.out.println(Objects.hash(user1) & (16 - 1));

        System.out.println(user2.hashCode());
        System.out.println(Objects.hash(user2) & (16 - 1));*/


        System.out.println("user hashcode = " + user.hashCode());
        System.out.print("index user = ");
        System.out.println(Objects.hash(user) & (16 - 1));

        System.out.println("user1 hashcode = " + user1.hashCode());
        System.out.print("index user1 = ");
        System.out.println(Objects.hash(user1) & (16 - 1));

        System.out.println("user2 hashcode = " + user2.hashCode());
        System.out.print("index user2 = ");
        System.out.println(Objects.hash(user2) & (16 - 1));
    }
}
