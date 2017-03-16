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

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DaoFactory daoFactory =new DaoFactory();
        Author author = new Author();

        author.setFirstName("Bdfffarsik");
        author.setLastName("Cat");
        author.setMiddle_name("Animal");

        Genre genre = new Genre();
        genre.setName("Боевик");


        AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
        GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
        genreDao.insert(genre);
        System.out.println(genre);



    }

}
