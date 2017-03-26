package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.MySqlBookInfo;
import nuris.epam.entity.*;
import nuris.epam.service.BookService;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.service.util.SqlDate;

import java.util.List;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(connectionPool.size());

        Genre genre = new Genre();
        genre.setId(1);
        Author author = new Author();
        author.setFirstName("Nuris");
        author.setLastName("Kalenov");
        author.setMiddleName("Temirovich");
        Book book = new Book();
        book.setGenre(genre);
        book.setAuthor(author);
        book.setDate(SqlDate.currentDateAndTime());
        book.setDescription("Давный давно");
        book.setIsbn("1236644");
        book.setName("Window");

        BookInfo bookInfo = new BookInfo();
        bookInfo.setAmount(100);
        bookInfo.setPrice(155);
        bookInfo.setBook(book);

        BookService bookService = new BookService();
      //  bookService.registerBook(bookInfo);

        BookInfo bookInfo1 = new BookInfo();
        bookInfo1.setId(1);

        System.out.println(bookService.findById(10));
        System.out.println(connectionPool.size());

    }
}
