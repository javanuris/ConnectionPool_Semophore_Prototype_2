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
    private DaoFactory daoFactory;
    private GeneralService generalService;

    CityService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        generalService = new GeneralService(new TypeDao().getCityDao(), daoFactory);
    }
    public City findByCity(int id) throws ServiceException {
        City city;
        city = (City) generalService.findById(id);
        return city;
    }

    public City insert(City city) throws ServiceException {
        city = (City) generalService.insert(city);
        return city;
    }

    public void update(City city) throws ServiceException {
        generalService.update(city);
    }

    public void delete(City city) throws ServiceException {
        generalService.delete(city);
    }

    public void findByPerson(Person person) throws ServiceException {
        try {
            DaoFactory daoFactory = new DaoFactory();

            CityDao cityDao  = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                    person.setCity(cityDao.findByPerson(person));
        } catch (DaoException e) {
            throw new ServiceException("Cannot getCity", e);
        } finally {
            daoFactory.returnConnect();
        }
    }

    public List<City> getAll() throws ServiceException {
        List<City> list;
        try {
            DaoFactory daoFactory = new DaoFactory();

            CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
            list = cityDao.getAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Cannot getAll", e);
        } finally {
            daoFactory.returnConnect();
        }
    }
}
