package fr.hamchez.roundnettracker.database.dao;

import java.util.List;

public interface DAO<T> {

    T get(int id);
    List<T> getAll();
    boolean insert(T object);
    T modify(T object);

}
