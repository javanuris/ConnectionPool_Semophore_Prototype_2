package nuris.epam.service;

import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDao;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;
import nuris.epam.entity.Person;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 21.03.2017.
 */
public class CustomerService {
    private DaoFactory daoFactory = new DaoFactory();
    private GeneralService generalService = new GeneralService(TypeDao.getInstance().getCustomerDao(), daoFactory);
    private CustomerRoleService customerRoleService = new CustomerRoleService();
    private PersonService personService = new PersonService(daoFactory);
    private AvatarService avatarService = new AvatarService(daoFactory);
    private String USER_ROLE = "user";

    public void registerCustomer(Customer customer) throws ServiceException {
        Person person = personService.insert(customer.getPerson());
        customer.setPerson(person);
        CustomerRole customerRole = customerRoleService.findRoleByName(USER_ROLE);
        customer.setCustomerRole(customerRole);
        insertCustomer(customer);
    }

    private void insertCustomer(Customer customer) throws ServiceException {
        generalService.insert(customer);
    }

    private void updateCustomer(Customer customer) throws ServiceException {
        generalService.update(customer);
    }




}
