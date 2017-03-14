package nuris.epam;
import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.Sql;
import nuris.epam.entity.Author;
import nuris.epam.entity.BaseEntity;
import nuris.epam.entity.Genre;
import nuris.epam.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        List<Genre> list = new ArrayList<>();
        DaoFactory daoFactory =new DaoFactory();
        DaoFactory daoFactory1 =new DaoFactory();
        DaoFactory daoFactory2 =new DaoFactory();
        DaoFactory daoFactory3 =new DaoFactory();
        daoFactory.returnConnect();
        DaoFactory daoFactory4 =new DaoFactory();
        DaoFactory daoFactory6 =new DaoFactory();

        BaseDao genreDao = daoFactory.getDao(daoFactory.typeDao().getGenreDao());

        System.out.println(connectionPool.size());
        System.out.println(genreDao.findById(1));



    }

}
