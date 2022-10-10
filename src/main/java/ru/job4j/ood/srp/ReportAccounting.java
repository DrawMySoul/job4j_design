package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class ReportAccounting implements Report {
    public static final String SALARY_FORMAT = "%.2f";

    private Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
            .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                .append(employee.getHired().getTime()).append(";")
                .append(employee.getFired().getTime()).append(";")
                .append(String.format(SALARY_FORMAT, employee.getSalary())).append(";")
                .append(System.lineSeparator());
        }
        return text.toString();
    }
}
