package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemStore<T extends Base> implements Store<T> {
	private final Map<String, T> mem = new HashMap<>();

	@Override
	public void add(T model) {
		mem.put(model.getId(), model);
	}

	@Override
	public boolean replace(String id, T model) {
		return Objects.equals(
			mem.computeIfPresent(id, (key, value) -> value = model),
			model
		);
	}

	@Override
	public boolean delete(String id) {
		boolean deleted = false;
		if (mem.containsKey(id)) {
			mem.remove(id);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public T findById(String id) {
		return mem.values().stream()
			.filter(m -> m.getId().equals(id))
			.findFirst()
			.orElse(null);
	}
}