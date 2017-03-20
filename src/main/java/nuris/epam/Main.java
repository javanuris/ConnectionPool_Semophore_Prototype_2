package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.CustomerDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.MySqlCustomer;
import nuris.epam.entity.*;
import nuris.epam.service.GenreService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        
        GenreService genreService = new GenreService();
        List<Genre> list = genreService.getAll();
        System.out.println(list.size());
    }
}
