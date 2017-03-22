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

    private String USER_ROLE = "user";


    public void registerCustomer(Customer customer) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        GeneralService generalService = new GeneralService(new TypeDao().getCustomerDao(), daoFactory);
        CustomerRoleService customerRoleService = new CustomerRoleService();
        PersonService personService = new PersonService(daoFactory);
        try {
            Person person = personService.insert(customer.getPerson());
            customer.setPerson(person);
            CustomerRole customerRole = customerRoleService.findRoleByName(USER_ROLE);
            customer.setCustomerRole(customerRole);
            generalService.insert(customer);
        } catch (ServiceException e) {
            throw new ServiceException("Cannot registerCustomer", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public Customer findById(int id) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        CustomerRoleService customerRoleService = new CustomerRoleService();
        PersonService personService = new PersonService(daoFactory);
        CityService cityService = new CityService(daoFactory);
        GeneralService generalService = new GeneralService(new TypeDao().getCustomerDao(), daoFactory);
        Customer customer;
        try {
            customer = (Customer) generalService.findById(id);
            fillCustomer(customerRoleService, personService, customer);
            return customer;
        } catch (ServiceException e) {
            throw new ServiceException("Can not find by ID", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public void updateCustomer(Customer customer) {
        DaoFactory daoFactory = new DaoFactory();
        GeneralService generalService = new GeneralService(new TypeDao().getCustomerDao(), daoFactory);
        try {
            generalService.update(customer);
        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {
            daoFactory.returnConnect();
        }

    }

    private void fillCustomer(CustomerRoleService customerRoleService, PersonService personService, Customer customer) {
        if (customer != null) {
            try {
                customerRoleService.findByRole(customer);
                personService.findByPerson(customer);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }

}
