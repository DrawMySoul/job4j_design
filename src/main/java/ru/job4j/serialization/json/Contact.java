package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Contact {
	private final String phone;

	public Contact(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "Contact{"
			+ "phone='" + phone + '\''
			+ '}';
	}

	public static void main(String[] args) {
		/*final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");

		*//* Преобразуем объект person в json-строку. *//*
		final Gson gson = new GsonBuilder().create();
		System.out.println(gson.toJson(person));

		*//* Модифицируем json-строку *//*
		final String personJson =
			"{"
				+ "\"sex\":false,"
				+ "\"age\":35,"
				+ "\"contact\":"
				+ "{"
				+ "\"phone\":\"+7(924)111-111-11-11\""
				+ "},"
				+ "\"statuses\":"
				+ "[\"Student\",\"Free\"]"
				+ "}";
		final Person personMod = gson.fromJson(personJson, Person.class);
		System.out.println(personMod);*/

		/* JSONObject из json-строки строки *//*
		JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

		*//* JSONArray из ArrayList *//*
		List<String> list = new ArrayList<>();
		list.add("Student");
		list.add("Free");
		JSONArray jsonStatuses = new JSONArray(list);

		*//* JSONObject напрямую методом put *//*
		final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sex", person.isSex());
		jsonObject.put("age", person.getAge());
		jsonObject.put("contact", jsonContact);
		jsonObject.put("statuses", jsonStatuses);

		*//* Выведем результат в консоль *//*
		System.out.println(jsonObject.toString());

		*//* Преобразуем объект person в json-строку *//*
		System.out.println(new JSONObject(person).toString());*/

		/*final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");

		JSONObject o = new JSONObject(person);
		System.out.println(o);

		Person person1 = new GsonBuilder().create().fromJson(o.toString(), Person.class);
		System.out.println(person1);
		System.out.println(person1.isSex());*/
	}
}
