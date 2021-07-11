package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> curMap = new HashMap<>();
        for (User user : current) {
            curMap.put(user.getId(), user.getName());
        }

        for (User user : previous) {
            if (curMap.containsKey(user.getId())
                    && user.getName().equals(curMap.get(user.getId()))) {
                curMap.remove(user.getId());
            } else if (curMap.containsKey(user.getId())
                    && !user.getName().equals(curMap.get(user.getId()))) {
                changed++;
                curMap.remove(user.getId());
            } else if (!curMap.containsKey(user.getId())) {
                deleted++;
            }
        }
        return new Info(curMap.size(), changed, deleted);
    }
}
