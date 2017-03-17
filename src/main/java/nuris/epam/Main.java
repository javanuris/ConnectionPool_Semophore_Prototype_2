package nuris.epam;
import nuris.epam.dao.CityDao;
import nuris.epam.dao.PersonDao;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.dao.mysql.*;
import nuris.epam.entity.City;
import nuris.epam.entity.Person;

import java.util.List;

/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        MySqlCity mysqlCity = new MySqlCity();
        PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
        City city = new City();
        Person person = new Person();
        person.setId(7);
        List<Person> list= personDao.getAll();
        MySqlPersonDao mySqlPersonDao = new MySqlPersonDao();
        System.out.println();


    }
}
