package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithViolation() {
        new Config("pair_with_violation.properties").load();
    }

    @Test
    public void whenPairWithComment() {
        Config config = new Config("pair_with_comment.properties");
        config.load();
        assertThat(config.value("#"), is(Matchers.nullValue()));
        assertThat(config.value(""), is(Matchers.nullValue()));
    }
}