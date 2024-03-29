package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportHRTest {
    @Test
    void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Misha", now, now, 98);
        store.add(worker);
        store.add(worker1);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
            .append("Name; Salary;")
            .append(System.lineSeparator())
            .append(worker.getName()).append(";")
            .append(worker.getSalary()).append(";")
            .append(System.lineSeparator())
            .append(worker1.getName()).append(";")
            .append(worker1.getSalary()).append(";")
            .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}