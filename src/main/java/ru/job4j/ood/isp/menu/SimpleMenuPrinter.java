package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    private static final String CHILD_TREE = "----";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItem : menu) {
            int count = menuItem.getNumber().split("\\.").length - 1;
            System.out.println(CHILD_TREE.repeat(count) + menuItem.getNumber() + menuItem.getName());
        }
    }
}
