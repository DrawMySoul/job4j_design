package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {

    @Test
    void whenTemplateIsNullThenMustGetException() {
        Generator generator = new IntroduceGenerator();
        Map<String, String> args = Map.of("name", "Jack");
        assertThrows(
            NullPointerException.class,
            () -> generator.produce(null, args)
        );
    }

    @Test
    void whenTemplateIsEmptyThenMustGetException() {
        Generator generator = new IntroduceGenerator();
        Map<String, String> args = Map.of("name", "Jack");
        assertThatThrownBy(() -> generator.produce("", args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("template is empty");
    }

    @Test
    void whenTemplateDoesNotContainKeysThenMustGetException() {
        Generator generator = new IntroduceGenerator();
        String template = "Hi, my name is";
        Map<String, String> args = Map.of("name", "Jack");
        assertThatThrownBy(() -> generator.produce("", args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("does not contains keys", template);
    }

    @Test
    void whenArgsIsNullThenMustGetException() {
        Generator generator = new IntroduceGenerator();
        String template = "Hi, my name is ${name}";
        assertThrows(
            NullPointerException.class,
            () -> generator.produce(template, null)
        );
    }

    @Test
    void whenArgsIsEmptyThenMustGetException() {
        Generator generator = new IntroduceGenerator();
        String template = "Hi, my name is ${name}";
        Map<String, String> args = Collections.emptyMap();
        assertThatThrownBy(() -> generator.produce(template, args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("args is null");
    }

    @Test
    void whenKeyOrValueIsEmptyThenMustGetException() {
        Generator generator = new IntroduceGenerator();
        String template = "Hi, my name is ${name}";
        Map<String, String> args = Map.of("", "");
        assertThatThrownBy(() -> generator.produce(template, args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("key is empty");
        assertThatThrownBy(() -> generator.produce(template, args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("value is empty");
    }

    @Test
    void whenTemplateContainsKeysMoreThanArgsThenMustGetException() {
        Generator generator = new IntroduceGenerator();
        String template = "Hi, my name is ${name} and I'm keen on ${hobby}";
        Map<String, String> args = Map.of("name", "Jack");
        assertThatThrownBy(() -> generator.produce(template, args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("template contains more keys")
            .hasMessageContaining(template);
    }

    @Test
    void whenArgsContainsKeysMoreThanTemplateThenMustGetException() {
        Generator generator = new IntroduceGenerator();
        String template = "Hi, my name is ${name}";
        Map<String, String> args = Map.of("name", "Jack", "hobby", "ships");
        assertThatThrownBy(() -> generator.produce(template, args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("args contains more keys")
            .hasMessageContaining(template);
    }

    @Test
    void whenValidData() {
        Generator generator = new IntroduceGenerator();
        String template = "Hi, my name is ${name} and I'm keen on ${hobby}";
        Map<String, String> args = Map.of("name", "Jack", "hobby", "ships");
        assertThat(generator.produce(template, args)).isNotNull()
            .isNotEmpty()
            .contains(args.get("name"), args.get("hobby"))
            .isEqualTo("Hi, my name is Jack and I'm keen on ships");
    }
}