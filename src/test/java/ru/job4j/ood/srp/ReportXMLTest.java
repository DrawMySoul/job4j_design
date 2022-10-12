package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportXMLTest {

    @Test
    void whenXMLGenerated() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportXML(store);
        StringBuilder expected = new StringBuilder();
        expected.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
            .append("<employees>")
            .append("<employee>")
            .append("<fired>")
            .append(df.format(worker.getHired().getTime()))
            .append("</fired>")
            .append("<hired>")
            .append(df.format(worker.getFired().getTime()))
            .append("</hired>")
            .append("<name>")
            .append(worker.getName())
            .append("</name>")
            .append("<salary>")
            .append(worker.getSalary())
            .append("</salary>")
            .append("</employee>")
            .append("</employees>");
        assertThat(engine.generate(em -> true)).isEqualTo(expected.toString());
    }
}