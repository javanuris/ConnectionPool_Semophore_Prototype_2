package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.entity.*;
import nuris.epam.service.AuthorService;
import nuris.epam.service.BookService;
import nuris.epam.service.CityService;
import nuris.epam.service.GenreService;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ConnectionPool connectionPool= ConnectionPool.getInstance();
        CityService cityService = new CityService();
        System.out.println(cityService.getAll());

    }
}
