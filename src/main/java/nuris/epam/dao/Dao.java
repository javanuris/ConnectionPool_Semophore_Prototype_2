package nuris.epam.dao;

import nuris.epam.entity.BaseEntity;

import java.util.List;

public interface Dao<T extends BaseEntity> {
    T insert(T item);

    T findById(int id);

    void update(T item);

    List<T> getAll();

    void delete(T item);

}
