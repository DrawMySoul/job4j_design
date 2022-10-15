package ru.job4j.ood.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportXML implements Report {

    private Store store;
    private JAXBContext context;
    private Marshaller marshaller;

    public ReportXML(Store store) {
        this.store = store;
        try {
            this.context = JAXBContext.newInstance(Employees.class);
            this.marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder xml = new StringBuilder();
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            xml.append(writer.getBuffer().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml.toString();
    }
}
