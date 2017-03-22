package nuris.epam.service;

import nuris.epam.dao.CustomerDao;
import nuris.epam.dao.PersonDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 21.03.2017.
 */
public class CustomerService {
    private String USER_ROLE = "user";

    public void registerCustomer(Customer customer) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                CustomerRoleService customerRoleService = new CustomerRoleService();
                CustomerRole customerRole = customerRoleService.findRoleByName(USER_ROLE);
                daoFactory.startTransaction();
                personDao.insert(customer.getPerson());
                customer.setCustomerRole(customerRole);
                customerDao.insert(customer);
                daoFactory.commitTransaction();
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("doesnt work rollback", e);
                }
                throw new ServiceException("cannot insert date to base", e);
            }
        }

    }

    public Customer findByCustomer(int id) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                Customer customer;
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                customer = customerDao.findById(id);
                fillCustomer(customer);
                return customer;
            } catch (DaoException e) {
                throw new ServiceException("cannot find date to base", e);
            }
        }
    }


    public void fillCustomer(Customer customer) throws ServiceException {
        PersonService personService = new PersonService();
        CustomerRoleService customerRoleService = new CustomerRoleService();
        CityService cityService = new CityService();
        personService.findByCustomer(customer);
        customerRoleService.findByCustomer(customer);
        cityService.findByPerson(customer.getPerson());

    }
}
