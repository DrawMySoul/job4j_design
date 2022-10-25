package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    private static final int ADD_ITEM = 0;
    private static final int ADD_SUB_ITEM = 1;
    private static final int SHOW_ITEMS = 2;
    private static final int EXIT = 3;
    private List<String> actions = List.of("Add item", "Add sub-item", "Show all items ", "Exit");

    private void showMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i));
        }
        System.out.print("Select: ");
    }

    private void resultOfAdding(boolean added) {
        System.out.println(added ? "Added" : "Not added!");
    }

    public static void main(String[] args) {
        TODOApp app = new TODOApp();
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            app.showMenu();
            int choose = scanner.nextInt();
            if (choose == ADD_ITEM) {
                System.out.println("Input item's name: ");
                String newItemName = scanner.next();
                boolean added = menu.add(Menu.ROOT, newItemName, STUB_ACTION);
                app.resultOfAdding(added);
            } else if (choose == ADD_SUB_ITEM) {
                System.out.print("Input parent's name: ");
                String parent = scanner.next();
                System.out.print("Input item's name: ");
                String newItemName = scanner.next();
                boolean added = menu.add(parent, newItemName, STUB_ACTION);
                app.resultOfAdding(added);
            } else if (choose == SHOW_ITEMS) {
                printer.print(menu);
            } else if (choose == EXIT) {
                isContinue = false;
            } else {
                System.out.println("Wrong input, try again");
            }
        }
    }
}
