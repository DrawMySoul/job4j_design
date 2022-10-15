package ru.job4j.ood.srp;

import com.google.gson.Gson;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    private Store store;
    private Gson gson;

    public ReportJSON(Store store) {
        this.store = store;
        this.gson = new Gson();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
