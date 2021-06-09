package ru.job4j.generics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {
	private final Map<String, T> mem = new HashMap<>();

	@Override
	public void add(T model) {
		mem.put(model.getId(), model);
	}

	@Override
	public boolean replace(String id, T model) {
		boolean replaced = false;
		for (Map.Entry<String, T> m : mem.entrySet()) {
			if (m.getKey().equals(id)) {
				mem.put(id, model);
				replaced = true;
			}
		}
		return replaced;
	}

	@Override
	public boolean delete(String id) {
		boolean deleted = false;
		Iterator<String> it = mem.keySet().iterator();
		while (it.hasNext()) {
			if (it.next().equals(id)) {
				it.remove();
				deleted = true;
			}
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