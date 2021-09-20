package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "pc")
@XmlAccessorType(XmlAccessType.FIELD)
public class PC {

	@XmlAttribute
	private boolean os;

	@XmlAttribute
	private String manufacturer;

	@XmlAttribute
	private double cost;
	private GraphicsCard graphicsCard;

	@XmlElementWrapper(name = "stores")
	@XmlElement(name = "store")
	private String[] stores;

	public PC() {
	}

	public PC(boolean os, String manufacturer, double cost, GraphicsCard graphicsCard, String... stores) {
		this.os = os;
		this.manufacturer = manufacturer;
		this.cost = cost;
		this.graphicsCard = graphicsCard;
		this.stores = stores;
	}

	@Override
	public String toString() {
		return "PC{"
			+ "os=" + os
			+ ", manufacturer=" + manufacturer
			+ ", cost=" + cost
			+ ", graphicsCard=" + graphicsCard
			+ ", stores" + Arrays.toString(stores)
			+ "}";
	}
}
