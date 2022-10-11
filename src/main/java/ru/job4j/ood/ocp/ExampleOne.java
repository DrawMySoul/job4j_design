package ru.job4j.ood.ocp;

import ru.job4j.ood.srp.Employee;
import ru.job4j.ood.srp.MemStore;
import ru.job4j.ood.srp.Report;

import java.util.function.Predicate;

public class ExampleOne {

    private class ReportOCP implements Report {
        MemStore store;

        public ReportOCP(MemStore store) {
            this.store = store;
        }

        @Override
        public String generate(Predicate<Employee> filter) {
            return null;
        }
    }
}
