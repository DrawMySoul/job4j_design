package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
            List.of("Купить продукты"), STUB_ACTION, "1."))
            .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
            "Купить продукты",
            List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
            .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
            "Покормить собаку", List.of(), STUB_ACTION, "2."))
            .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    void whenAddThenSelectSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add(Menu.ROOT, "Изучить модуль ISP", STUB_ACTION);
        menu.add("Изучить модуль ISP", "Выполнить задание: 0. Принцип разделения интерфейсов", STUB_ACTION);
        menu.add("Изучить модуль ISP", "Выполнить задание: 1. Создать меню.", STUB_ACTION);
        assertThat(menu.select("Купить хлеб"))
            .isEqualTo(Optional.of(
                new Menu.MenuItemInfo("Купить хлеб", List.of(), STUB_ACTION, "1.1.1."))
            );
        assertThat(menu.select("Выполнить задание: 0. Принцип разделения интерфейсов")).isEqualTo(Optional.of(
            new Menu.MenuItemInfo("Выполнить задание: 0. Принцип разделения интерфейсов",
                List.of(),
                STUB_ACTION,
                "2.1.")
        ));
        assertThat(menu.select("Выполнить задание: 1. Создать меню.")).isEqualTo(Optional.of(
            new Menu.MenuItemInfo("Выполнить задание: 1. Создать меню.",
                List.of(),
                STUB_ACTION,
                "2.2.")
        ));
    }

    @Test
    void whenDidNotAddButSelectedThenMustGetException() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Изучить модуль ISP", STUB_ACTION);
        menu.add("Изучить модуль ISP", "Выполнить задание: 0. Принцип разделения интерфейсов", STUB_ACTION);
        assertThat(menu.select("Выполнить задание: 1. Создать меню.")).isNotPresent();
    }

    @Test
    void whenPrintMenu() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add(Menu.ROOT, "Изучить модуль ISP", STUB_ACTION);
        menu.add("Изучить модуль ISP", "Выполнить задание: 0. Принцип разделения интерфейсов", STUB_ACTION);
        menu.add("Изучить модуль ISP", "Выполнить задание: 1. Создать меню.", STUB_ACTION);
        MenuPrinter printer = new SimpleMenuPrinter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        printer.print(menu);
        StringBuilder expected = new StringBuilder();
        expected.append("1.Сходить в магазин").append(NEW_LINE)
            .append("----1.1.Купить продукты").append(NEW_LINE)
            .append("--------1.1.1.Купить хлеб").append(NEW_LINE)
            .append("2.Изучить модуль ISP").append(NEW_LINE)
            .append("----2.1.Выполнить задание: 0. Принцип разделения интерфейсов").append(NEW_LINE)
            .append("----2.2.Выполнить задание: 1. Создать меню.").append(NEW_LINE);
        assertThat(out.toString()).hasToString(expected.toString());
    }

}