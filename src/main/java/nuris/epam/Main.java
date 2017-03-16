package nuris.epam;
import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.AuthorDao;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.GenreDao;
import nuris.epam.dao.PublisherDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.MySqlAuthorDao;
import nuris.epam.dao.mysql.MySqlGenreDao;
import nuris.epam.dao.mysql.MySqlPublisherDao;
import nuris.epam.dao.mysql.Sql;
import nuris.epam.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DaoFactory daoFactory =new DaoFactory();
        Book book = new Book();
        book.setId(5);
        AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
        GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
        PublisherDao publisherDao = (PublisherDao) daoFactory.getDao(daoFactory.typeDao().getPublisherDao());
        Publisher publisher= publisherDao.findByBook(book);
        System.out.println(publisher);
    }

}
