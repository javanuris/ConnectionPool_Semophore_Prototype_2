package nuris.epam.dao.manager;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.PublisherDao;
import nuris.epam.dao.mysql.MySqlAuthorDao;
import nuris.epam.dao.mysql.MySqlGenreDao;
import nuris.epam.dao.mysql.MySqlPublisherDao;

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
    public Class getPublisherDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlPublisherDao.class;
        } else {
            return MySqlPublisherDao.class;
        }
    }
    public Class getGenreDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlGenreDao.class;
        } else {
            return MySqlGenreDao.class;
        }
    }
    public static TypeDao getInstance() {
        if (typeDao == null) {
            typeDao = new TypeDao();
        }
        return typeDao;
    }
}
