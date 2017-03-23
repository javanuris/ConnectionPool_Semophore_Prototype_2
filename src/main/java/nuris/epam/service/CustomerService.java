package nuris.epam.service;

import nuris.epam.dao.CityDao;
import nuris.epam.dao.CustomerDao;
import nuris.epam.dao.CustomerRoleDao;
import nuris.epam.dao.PersonDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.City;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;
import nuris.epam.entity.Person;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.service.util.SqlDate;

import java.util.List;

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
                CustomerRoleDao customerRoleDao = (CustomerRoleDao) daoFactory.getDao(daoFactory.typeDao().getCustomerRoleDao());
                CustomerRole customerRole = customerRoleDao.findRoleByName(USER_ROLE);
                daoFactory.startTransaction();
                personDao.insert(customer.getPerson());
                customer.setCustomerRole(customerRole);
                customer.setRegisterDate(SqlDate.currentDateAndTime());
                customerDao.insert(customer);
                daoFactory.commitTransaction();

            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("doesn't work rollback", e);
                }
                throw new ServiceException("cannot insert date to base", e);
            }
        }
    }

    public void updateCustomer(Customer customer) throws ServiceException{
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());

                daoFactory.startTransaction();
                Person person = personDao.findByCustomer(customer);
                personDao.update(person);
                customerDao.update(customer);
                daoFactory.commitTransaction();

            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("doesn't work rollback(delete)", e);
                }
                throw new ServiceException("can't delete date from base(updateCustomer())", e);
            }
        }

    }

    public void deleteCustomer(Customer customer) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                Person person = personDao.findByCustomer(customer);

                daoFactory.startTransaction();
                customerDao.delete(customer);
                personDao.delete(person);
                daoFactory.commitTransaction();

            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("doesn't work rollback(delete)", e);
                }
                throw new ServiceException("can't delete date from base(deleteCustomer())", e);
            }
        }
    }


    public Customer findCustomer(int id) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                Customer customer;
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                customer = customerDao.findById(id);
                fillCustomer(customer);
                return customer;
            } catch (DaoException e) {
                throw new ServiceException("can't find date in base", e);
            }
        }
    }

    public void fillCustomer(Customer customer) throws ServiceException {
        try {
            if(customer!=null) {
                try (DaoFactory daoFactory = new DaoFactory()) {

                    PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                    CustomerRoleDao customerRoleDao = (CustomerRoleDao) daoFactory.getDao(daoFactory.typeDao().getCustomerRoleDao());
                    CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());

                    Person person = personDao.findByCustomer(customer);
                    person.setCity(cityDao.findByPerson(person));
                    customer.setPerson(person);
                    customer.setCustomerRole(customerRoleDao.findByCustomer(customer));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't fill  date", e);
        }
    }

    public List<City> getAllCity() throws ServiceException {
        List<City> list;
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                list = cityDao.getAll();
                return list;
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't getAllCity date", e);
        }
    }

}
