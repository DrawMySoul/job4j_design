package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.ood.srp.ReportEngine.DATE_FORMAT;

class ReportEngineTest {
    @Test
    void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
            .append("Name; Hired; Fired; Salary;")
            .append(System.lineSeparator())
            .append(worker.getName()).append(";")
            .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
            .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
            .append(worker.getSalary()).append(";")
            .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}