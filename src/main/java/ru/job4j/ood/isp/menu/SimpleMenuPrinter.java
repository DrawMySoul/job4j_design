package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    private static final String CHILD_TREE = "----";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String ROOT_TEMPLATE = "%s%s%s";
    private static final String CHILD_TEMPLATE = "%s ".concat(ROOT_TEMPLATE);

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItem : menu) {
            int count = menuItem.getNumber().split("\\.").length - 1;
            if (count == 0) {
                System.out.printf(ROOT_TEMPLATE, menuItem.getNumber(), menuItem.getName(), NEW_LINE);
            } else if (count > 0) {
                System.out.printf(CHILD_TEMPLATE, CHILD_TREE.repeat(count), menuItem.getNumber(), menuItem.getName(), NEW_LINE);
            }
        }
    }
}
