package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.entity.*;
import nuris.epam.service.AuthorService;
import nuris.epam.service.BookService;
import nuris.epam.service.GenreService;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ConnectionPool connectionPool= ConnectionPool.getInstance();

        BookService bookService = new BookService();



        Author author =new Author();
        author.setId(9);
        author.setFirstName("Temit");
        author.setMiddleName("BIG");
        author.setLastName("LITTLE");

        System.out.println(bookService.findById(11));
        System.out.println(connectionPool.size());

    }
}
