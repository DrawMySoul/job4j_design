package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class ReportDev implements Report {

    private Store store;

    public ReportDev(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html><body>")
            .append(System.lineSeparator())
            .append("<h2>Name; Hired; Fired; Salary;</h2>")
            .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<h3>");
            text.append(employee.getName()).append(";")
                .append(employee.getHired().getTime()).append(";")
                .append(employee.getFired().getTime()).append(";")
                .append(employee.getSalary()).append(";")
                .append("</h3>")
                .append(System.lineSeparator());
        }
        text.append("</body></html>");
        return text.toString();
    }
}
