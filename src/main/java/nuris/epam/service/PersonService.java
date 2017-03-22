package nuris.epam.service;

import nuris.epam.dao.PersonDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDao;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;
import nuris.epam.service.exception.ServiceException;

/**
 * Created by User on 20.03.2017.
 */
public class PersonService {
    DaoFactory daoFactory;
    private GeneralService generalService;
    PersonService(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
        generalService = new GeneralService(new TypeDao().getPersonDao(), daoFactory);
    }
    public Person findById(int id) throws ServiceException {
        Person person;
        person = (Person) generalService.findById(id);
        return person;
    }

    public Person insert(Person person) throws ServiceException {
        person = (Person) generalService.insert(person);
        return person;
    }

    public void update(Person person) throws ServiceException {
        generalService.update(person);
    }

    public void delete(Person person) throws ServiceException {
        generalService.delete(person);
    }

    public void findByPerson(Customer customer) throws ServiceException {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
            customer.setPerson(personDao.findByCustomer(customer));
        } catch (DaoException e) {
            throw new ServiceException("Cannot find by Avatar", e);
        }finally {
            daoFactory.returnConnect();
        }

    }
}
