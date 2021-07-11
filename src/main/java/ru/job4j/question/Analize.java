package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> some = new HashMap<>();
        for (User user : current) {
            some.put(user.getId(), user.getName());
        }

        for (User user : previous) {
            if (some.containsKey(user.getId())
                    && user.getName().equals(some.get(user.getId()))) {
                some.remove(user.getId());
            } else if (some.containsKey(user.getId())
                    && !user.getName().equals(some.get(user.getId()))) {
                changed++;
                some.remove(user.getId());
            } else if (!some.containsKey(user.getId())) {
                deleted++;
            }
        }
        return new Info(some.size(), changed, deleted);
    }
}
