package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "graphicsCard")

public class GraphicsCard {
	@XmlAttribute
	private String model;

	@XmlAttribute
	private int videoMemorySize;

	public GraphicsCard() {
	}

	public GraphicsCard(String model, int videoMemorySize) {
		this.model = model;
		this.videoMemorySize = videoMemorySize;
	}

	@Override
	public String toString() {
		return "GraphicsCard{"
			+ "model=" + model
			+ ", videoMemorySize=" + videoMemorySize
			+ "}";
	}
}
