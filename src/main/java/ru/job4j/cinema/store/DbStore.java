package ru.job4j.cinema.store;

import java.util.Collection;

public interface DbStore<T> {

    Collection<T> getAll();

    void save(T item);

    T getById(int id);

    void deleteById(int id);
}
