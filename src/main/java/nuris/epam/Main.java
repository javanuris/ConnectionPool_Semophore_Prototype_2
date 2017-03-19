package nuris.epam;

import nuris.epam.dao.CustomerDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.MySqlCustomer;
import nuris.epam.entity.*;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        MySqlCustomer mySqlCustomerDao = new MySqlCustomer();
        DaoFactory daoFactory = new DaoFactory();
        CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
        System.out.println(mySqlCustomerDao.FIND_BY_LOGIN_PASSWORD);
        Customer customer = new Customer();
        customer.setRegisterDate(new Date(Calendar.getInstance().getTime().getTime()));
        customer.setLogin("frfrfrr");
        customer.setPassword("77788899");
        customer.setId(3);
        Avatar avatar = new Avatar();
        avatar.setId(1);
        customer.setAvatar(avatar);
        Person person = new Person();
        person.setId(5);
        CustomerRole customerRole = new CustomerRole();
        customerRole.setId(1);
        customer.setPerson(person);
        customer.setCustomerRole(customerRole);

        Customer customer1 = customerDao.getCustomer("frfrfrr" , "77788899");
        System.out.println(customer1);

    }
}
