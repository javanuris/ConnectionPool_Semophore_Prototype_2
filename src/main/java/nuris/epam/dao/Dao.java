package nuris.epam.dao;

import nuris.epam.entity.BaseEntity;

import java.util.List;

public interface Dao<T extends BaseEntity> {
    int insert(T e);

    T findById(int id);

    void update(T item);

    List<T> getAll();

    void delete(int id);

    void delete(T item);
}
