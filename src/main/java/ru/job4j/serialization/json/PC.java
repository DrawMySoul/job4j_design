package ru.job4j.serialization.json;

import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class PC {
	private final boolean os;
	private final String manufacturer;
	private final double cost;
	private final GraphicsCard graphicsCard;
	private final String[] stores;

	public PC(boolean os, String manufacturer, double cost, GraphicsCard graphicsCard, String... stores) {
		this.os = os;
		this.manufacturer = manufacturer;
		this.cost = cost;
		this.graphicsCard = graphicsCard;
		this.stores = stores;
	}

	public boolean isOs() {
		return os;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public double getCost() {
		return cost;
	}

	public GraphicsCard getGraphicsCard() {
		return graphicsCard;
	}

	public String[] getStores() {
		return stores;
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

	public static void main(String[] args) {
		final PC pc = new PC(true, "ASUS", 1499.99, new GraphicsCard("3070TI", 8), "DNS", "Ситилинк");

		JSONObject pcJSON = new JSONObject();
		pcJSON.put("os", pc.isOs());
		pcJSON.put("manufacturer", pc.getManufacturer());
		pcJSON.put("cost", pc.getCost());
		pcJSON.put("graphicsCard", new GraphicsCard("1660", 4));
		pcJSON.put("stores", Arrays.asList("NIX", "E-catalog"));

		System.out.println(pcJSON.toString());
		System.out.println(new JSONObject(pc).toString());

		/*final Gson gson = new GsonBuilder().create();
		String jsonPC = gson.toJson(pc);
		System.out.println(jsonPC);
		final PC pcMod  = gson.fromJson(jsonPC, PC.class);
		System.out.println(pcMod);*/
	}
}
