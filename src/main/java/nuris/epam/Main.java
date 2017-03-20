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
        connectionPool.size();
        AuthorService authorService = new AuthorService();
        System.out.println(authorService.findByAuthor2(2));

        System.out.println(connectionPool.size());

        Author author =new Author();
        author.setFirstName("Temit");
        author.setMiddleName("BIG");
        author.setLastName("LITTLE");

        System.out.println(authorService.insert2(author));
    }
}
