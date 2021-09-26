package ru.job4j.serialization.json;

public class GraphicsCard {
	private final String model;
	private final int videoMemorySize;

	public GraphicsCard(String model, int videoMemorySize) {
		this.model = model;
		this.videoMemorySize = videoMemorySize;
	}

	public String getModel() {
		return model;
	}

	public int getVideoMemorySize() {
		return videoMemorySize;
	}

	@Override
	public String toString() {
		return "GraphicsCard{"
			+ "model=" + model
			+ ", videoMemorySize=" + videoMemorySize
			+ "}";
	}
}
