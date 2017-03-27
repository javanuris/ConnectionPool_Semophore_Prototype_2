package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.MySqlBookInfo;
import nuris.epam.dao.mysql.MySqlTransaction;
import nuris.epam.entity.*;
import nuris.epam.service.BookService;
import nuris.epam.service.CustomerService;
import nuris.epam.service.TransactionService;
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
        bookInfo.setId(10);


        BookService bookService = new BookService();

        TransactionService transactionService = new TransactionService();

        Customer customer = new Customer();
        customer.setId(7);
        Transaction transaction =  new Transaction();
        transaction.setId(12);
        transaction.setCustomer(customer);
        transaction.setBookInfo(bookInfo);
       // transactionService.takeBook(transaction);
        transactionService.returnBook(transaction, customer);
        System.out.println(connectionPool.size());

    }
}
