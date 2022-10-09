package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportDevTest {
    @Test
    void whenDevGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportDev(store);
        StringBuilder expect = new StringBuilder()
            .append("<html><body>")
            .append(System.lineSeparator())
            .append("<h2>Name; Hired; Fired; Salary;</h2>")
            .append(System.lineSeparator())
            .append("<h3>")
            .append(worker.getName()).append(";")
            .append(worker.getHired().getTime()).append(";")
            .append(worker.getFired().getTime()).append(";")
            .append(worker.getSalary()).append(";")
            .append("</h3>")
            .append(System.lineSeparator())
            .append("</body></html>");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}