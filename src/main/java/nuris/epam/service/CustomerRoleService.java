package nuris.epam.service;

import nuris.epam.dao.CustomerRoleDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDao;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 21.03.2017.
 */
public class CustomerRoleService {

    public void findByCustomer(Customer customer) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CustomerRoleDao customerRoleDao = (CustomerRoleDao) daoFactory.getDao(daoFactory.typeDao().getCustomerRoleDao());
                customer.setCustomerRole(customerRoleDao.findByCustomer(customer));
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }

    public CustomerRole findRoleByName(String roleName) throws ServiceException{
        DaoFactory daoFactory = new DaoFactory();
        CustomerRole customerRole;
        try {
            CustomerRoleDao customerRoleDao = (CustomerRoleDao) daoFactory.getDao(daoFactory.typeDao().getCustomerRoleDao());
            customerRole = customerRoleDao.findRoleByName(roleName);
            return customerRole;
        } catch (DaoException e) {
            throw new ServiceException("Cannot find by Role", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
}
