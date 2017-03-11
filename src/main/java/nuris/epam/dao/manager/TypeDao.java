package nuris.epam.dao.manager;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.MySqlAuthorDao;

/**
 * Created by User on 11.03.2017.
 */
public class TypeDao {
    private static TypeDao typeDao;
    private ConnectionPool connectType;

    private TypeDao() {
        connectType = ConnectionPool.getInstance();
    }

    public Class getAuthorDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlAuthorDao.class;
        } else {
            return MySqlAuthorDao.class;
        }
    }

    public static TypeDao getInstance() {
        if (typeDao == null) {
            typeDao = new TypeDao();
        }
        return typeDao;
    }
}
