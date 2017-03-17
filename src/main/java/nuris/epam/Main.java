package nuris.epam;
import nuris.epam.dao.BookDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.*;
import nuris.epam.entity.Genre;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        MySqlBook mySqlBook = new MySqlBook();
        BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
        System.out.println(mySqlBook.sql());
        Genre genre =new Genre();
        genre.setId(1);
        System.out.println(bookDao.getLimitBookByGenre(genre,2,2));

    }
}
