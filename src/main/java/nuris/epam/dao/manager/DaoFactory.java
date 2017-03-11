package nuris.epam.dao.manager;


import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.BaseDao;

import java.sql.Connection;

/**
 * Created by User on 10.03.2017.
 */
public class DaoFactory {

    private static DaoFactory daoFactory;
    private ConnectionPool connectionPool;
    private Connection connection;

    private DaoFactory() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

   public <T extends BaseDao> T getDao(Class<T> clazz) throws Exception {
        T t;
        try {
            t = clazz.newInstance();
            t.setConnection(connection);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new Exception();
        }
        return t;
    }


    public static DaoFactory getInstance() {
        if (null == daoFactory) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }


}
