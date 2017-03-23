package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.entity.*;
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
        person.setCity(city);
       // person.setId(14);
        person.setFirstName("Mars");
        person.setLastName("Moon");
        person.setMiddleName("Zooooo");
        person.setPhone("Zooooo//Zooooo");
        person.setAdreess("NUROSPASWORF");
        person.setBirthday(SqlDate.stringToDate("1996-07-08"));

      CustomerService customers = new CustomerService();
        Customer  customer = customers.findCustomer(341);
        customer.setPerson(person);
        customer.setPassword("Zooooo");
        customer.setLogin("Mama");
        customerService.updateCustomer(customer);
        System.out.println(customerService.findCustomer(341));
        System.out.println(connectionPool.size());


    }
}
