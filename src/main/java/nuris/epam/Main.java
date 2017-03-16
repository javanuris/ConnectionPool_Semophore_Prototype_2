package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.*;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.*;
import nuris.epam.entity.*;

import java.lang.reflect.GenericArrayType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DaoFactory daoFactory = new DaoFactory();

        MySqlBook mySqlBook = new MySqlBook();
        Author author = new Author();
        Publisher publisher = new Publisher();
        Genre genre = new Genre();

        genre.setId(1);
        publisher.setId(1);
        author.setId(2);
        List<Book> list = new ArrayList<>();

        Book book = new Book();


        book.setId(5);

        book.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        book.setName("Morfi");
        book.setIsbn(122325);
        book.setGenre(genre);
        book.setAuthor(author);
        book.setPublisher(publisher);



        BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
        System.out.println(bookDao.findById(7));


    }

}
