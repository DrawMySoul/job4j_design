package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().map(s -> s.split(";"))
                .filter(this::chekStrings)
                .forEach(strings -> users.add(new User(strings[0], strings[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private boolean chekStrings(String[] strings) {
        if (strings.length < 2) {
            throw new IllegalArgumentException("String contain less than 2 elements");
        } else if (strings[0].isEmpty() || strings[1].isEmpty()) {
            throw new IllegalArgumentException("Name or Email is empty");
        } else if (!strings[0].matches("\\w+\\s\\w+") || !strings[1].matches(".+\\@.+\\..+")) {
            throw new IllegalArgumentException("Wrong data format");
        }
        return true;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
            cfg.getProperty("jdbc.url"),
            cfg.getProperty("jdbc.username"),
            cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./src/main/resources/ps_app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
