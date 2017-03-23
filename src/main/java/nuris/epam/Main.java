package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.entity.*;
import nuris.epam.service.BookService;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.service.util.SqlDate;

import java.util.Calendar;

/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(connectionPool.size());
        CustomerService customerService = new CustomerService();
        CustomerRole customerRole = new CustomerRole();
        customerRole.setId(1);
        City city = new City();
        city.setId(1);
        Person person = new Person();


      CustomerService customers = new CustomerService();
        person.setCity(city);
        person.setFirstName("Mars");
        person.setLastName("LUUN");
        person.setMiddleName("Zooooппппo");
        person.setPhone("Zooooo//Zooooo");
        person.setAdreess("NUROSPASWORF");
        person.setBirthday(SqlDate.stringToDate("1997-07-08"));

        System.out.println(connectionPool.size());
        BookService bookService = new BookService();
        Book book = bookService.findById(9);
        System.out.println(book);

    }
}
