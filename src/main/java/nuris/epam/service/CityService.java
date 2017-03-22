package nuris.epam.service;

import nuris.epam.dao.CityDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.manager.TypeDao;
import nuris.epam.entity.City;
import nuris.epam.entity.Person;
import nuris.epam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class CityService {

    public City insert(City city) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                city = cityDao.insert(city);
                return city;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot insert date", e);
        }
    }

    public void update(City city) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                cityDao.update(city);
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }

    public void delete(City city) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                cityDao.delete(city);
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }

    public void findByPerson(Person person) throws ServiceException {
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                person.setCity(cityDao.findByPerson(person));
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update date", e);
        }
    }

    public List<City> getAll() throws ServiceException {
        List<City> list;
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                list = cityDao.getAll();
                return list;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot getAll date", e);
        }
    }

    public City findById(int id) throws ServiceException {
        City city;
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                city = cityDao.findById(id);
                return city;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot id date", e);
        }
    }

}
