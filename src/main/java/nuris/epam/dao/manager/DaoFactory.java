package nuris.epam.dao.manager;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.BaseEntity;

import java.sql.Connection;

/**
 * Класс служит для управленеи коннектами, а именно раздача и закрытие коннектов.
 * Также создает экземпляры Dao обектов и  кладет в них коннект.
 *
 * @author Kalenov Nurislam
 */
public class DaoFactory {
    /**
     * Поле  - содержит пул коннектов
     */
    private ConnectionPool connectionPool;
    /**
     * Поле  - Соеденение с БД.
     */
    private Connection connection;
    /**
     * Поле  - Возвращяет обект дао в зависимости от типа БД.
     */
    private TypeDao typeDao;

    public DaoFactory() {
        connectionPool = ConnectionPool.getInstance();
        typeDao = TypeDao.getInstance();
        connection = connectionPool.getConnection();
    }

    /**
     * Создает новый объект Dao , также дает ему коннект к базе данных.
     *
     * @param clazz - Тип обекта. (Рефлексия)
     * @return Dao обьект.
     */
    public <T extends BaseDao<BaseEntity>> T getDao(Class<T> clazz) throws DaoException {
        T t;
        try {
            t = clazz.newInstance();
            t.setConnection(connection);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new DaoException("Cant to create or give new DAO object", e);
        }
        return t;
    }

    /**
     * Кладет коннект в пул коннектов.
     */
    public void returnConnect() {
        connectionPool.returnConnection(connection);
    }

    /**
     * @return Возвращяет Дао обект в зависимости от типа БД.
     */
    public TypeDao typeDao() {
        return typeDao;
    }


}
