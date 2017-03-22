package nuris.epam.service;

import nuris.epam.dao.GenreDao;
import nuris.epam.dao.PersonDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDao;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;
import nuris.epam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class PersonService {

    public Person insert(Person person) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                person = personDao.insert(person);
                return person;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot insert date", e);
        }
    }

    public void update(Person person) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                personDao.update(person);
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }

    public void delete(Person person) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                personDao.delete(person);
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }

    public void findByCustomer(Customer customer) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                customer.setPerson(personDao.findByCustomer(customer));
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }



}
