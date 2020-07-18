package fr.laPouleQuiMousse.controllers;

import org.springframework.data.domain.Sort;

public class SortAdapter {

    private Sort sort;

    public SortAdapter(Sort sort) {
        this.sort = sort;
    }

    public Sort.Order getOrderFor(String property) {
        return sort.getOrderFor(property);
    }

    public boolean isSorted() {
        return sort.isSorted();
    }

    @Override
    public String toString() {
        String sortParam = null;
        String defaultString = sort.toString();
        if (defaultString != null && !defaultString.equals("UNSORTED")) {
            sortParam = defaultString.replace(": ", ",");
        }
        return sortParam;
    }
}
