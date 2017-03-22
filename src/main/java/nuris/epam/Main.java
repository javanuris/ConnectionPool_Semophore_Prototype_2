package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.entity.*;
import nuris.epam.service.CustomerService;
import nuris.epam.service.PersonService;
import nuris.epam.service.exception.ServiceException;

import java.util.Calendar;

/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(connectionPool.size());
        CustomerService customerService = new CustomerService();
        CustomerRole customerRole = new CustomerRole();
        customerRole.setId(1);
        City city = new City();
        city.setId(1);
        Person person = new Person();
        person.setCity(city);
        person.setId(14);
        person.setFirstName("Mars");
        person.setLastName("Moon");
        person.setMiddleName("NUROSPASWORF");
        person.setPhone("NUROSPASWORF//NUROSPASWORF");
        person.setAdreess("NUROSPASWORF");
        person.setBirthday(date);

        Customer customer = new Customer();
        customer.setId(3);
        customer.setPerson(person);
        customer.setPassword("NURIS");
        customer.setLogin("Mars");
        customer.setRegisterDate(date);


        customerService.registerCustomer(customer);
        System.out.println(customerService.findByCustomer(3));
        System.out.println(connectionPool.size());


    }
}
