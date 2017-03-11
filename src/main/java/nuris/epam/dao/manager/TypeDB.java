package nuris.epam.dao.manager;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.MySqlAuthorDao;

/**
 * Created by User on 11.03.2017.
 */
public class TypeDB {
    private static TypeDB typeDB;
    private ConnectionPool connectType;

    private TypeDB() {
        connectType = ConnectionPool.getInstance();
    }

    public Class getAuthorDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlAuthorDao.class;
        } else {
            return MySqlAuthorDao.class;
        }
    }

    public static TypeDB getInstance() {
        if (typeDB == null) {
            typeDB = new TypeDB();
        }
        return typeDB;
    }
}
