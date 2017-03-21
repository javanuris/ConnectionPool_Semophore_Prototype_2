package nuris.epam;

import nuris.epam.entity.City;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;
import nuris.epam.entity.Person;
import nuris.epam.service.BookService;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;
import java.util.Calendar;

/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        CustomerService customerService = new CustomerService();
        CustomerRole customerRole = new CustomerRole();
        customerRole.setId(1);
        City city = new City();
        city.setId(1);
        Person person = new Person();
        person.setCity(city);
        person.setId(5);
        person.setFirstName("NUR");
        person.setLastName("XXX");
        person.setMiddleName("Zhanerke");
        person.setPhone("999999");
        person.setAdrees("Lugavaia 13");
        person.setBirthday(date);

        Customer customer = new Customer();
        customer.setId(3);
        customer.setPerson(person);
        customer.setPassword("877799");
        customer.setLogin("kal77nov");
        customer.setRegisterDate(date);
        customer.setCustomerRole(customerRole);

        customerService.changeInfoCustomer(customer);


    }
}
