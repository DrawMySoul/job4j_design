package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainForMyXML {
	public static void main(String[] args) throws Exception {
		PC pc = new PC(true, "ASUS", 1499.99, new GraphicsCard("3070TI", 8), "DNS", "Ситилинк");

		JAXBContext context = JAXBContext.newInstance(PC.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		String xml = "";

		try (StringWriter writer = new StringWriter()) {
			marshaller.marshal(pc, writer);
			xml = writer.getBuffer().toString();
		}
		System.out.println(xml);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		try (StringReader reader = new StringReader(xml)) {
			PC result = (PC) unmarshaller.unmarshal(reader);
		}
	}
}
