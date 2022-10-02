package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("no data");
    }

    @Test
    void whenNamesArrayIsEmptyThenMustGetException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
            .isInstanceOf(IllegalArgumentException.class)
            .message()
            .isNotEmpty();
    }

    @Test
    void whenNameDoesNotContainsSymbolEqualsThenMustGetException() {
        NameLoad nameLoad = new NameLoad();
        String name = "keyvalue";
        assertThatThrownBy(() -> nameLoad.parse(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(name)
            .hasMessageContaining("not contain the symbol");
    }

    @Test
    void whenNameDoesNotContainKeyThenMustGetException() {
        NameLoad nameLoad = new NameLoad();
        String name = "=value";
        assertThatThrownBy(() -> nameLoad.parse(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(name)
            .hasMessageContaining("not contain a key");
    }

    @Test
    void whenNameDoesNotContainValueThenMustGetException() {
        NameLoad nameLoad = new NameLoad();
        String name = "key=";
        assertThatThrownBy(() -> nameLoad.parse(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(name)
            .hasMessageContaining("not contain a value");
    }
}