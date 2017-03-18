package nuris.epam.dao.manager;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.*;

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

    public Class getBookDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlBook.class;
        } else {
            return MySqlBook.class;
        }
    }

    public Class getCityDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlCity.class;
        } else {
            return MySqlCity.class;
        }
    }

    public Class getPersonDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlPersonDao.class;
        } else {
            return MySqlPersonDao.class;
        }
    }
    public Class getCustomerRoleDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlCustomerRole.class;
        } else {
            return MySqlCustomerRole.class;
        }
    }

    public Class getAvatarDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlAvatarDao.class;
        } else {
            return MySqlAvatarDao.class;
        }
    }


    public static TypeDao getInstance() {
        if (typeDao == null) {
            typeDao = new TypeDao();
        }
        return typeDao;
    }
}
