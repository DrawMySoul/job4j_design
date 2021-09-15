package ru.job4j.serialization.json;

public class GraphicsCard {
	private final String model;
	private final int videoMemorySize;

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
