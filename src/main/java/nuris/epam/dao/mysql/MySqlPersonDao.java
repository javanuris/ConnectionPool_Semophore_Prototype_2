package nuris.epam.dao.mysql;

import nuris.epam.dao.PersonDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;

import java.util.List;

/**
 * Created by User on 17.03.2017.
 */
public class MySqlPersonDao extends PersonDao {

    private static final String PERSON = "person";
    private static final String ID_PERSON = "id_person";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String PHONE = "phone";
    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String ID_CITY = "id_city";
    private static final String CUSTOMER = "customer";
    private static final String ID_CUSTOMER = "id_customer";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(PERSON).whereQs(ID_PERSON).build();
    private static final String INSERT = Sql.create().insert().var(PERSON).values(ID_PERSON, 1).build();
    private static final String UPDATE = Sql.create().update().var(PERSON).set().varQs(FIRST_NAME).c().varQs(LAST_NAME).c().varQs(MIDDLE_NAME).c().varQs(PHONE).c().varQs(BIRTHDAY).c().varQs(ADDRESS).c().varQs(ID_CITY).whereQs(ID_PERSON).build();
    private static final String DELETE = Sql.create().delete().var(PERSON).whereQs(ID_PERSON).build();
    private static final String SELECT_ALL = Sql.create().select().allFrom().var(PERSON).build();
    private static final String FIND_BY_CUSTOMER = Sql.create().select().varS(PERSON, ID_PERSON).c().varS(PERSON, FIRST_NAME).c().varS(PERSON, LAST_NAME).c().varS(PERSON, MIDDLE_NAME).c().varS(PERSON, PHONE).c().varS(PERSON, BIRTHDAY).c().varS(PERSON, ADDRESS).from().var(PERSON).join(CUSTOMER).varS(CUSTOMER, ID_PERSON).eq().varS(PERSON, ID_PERSON).whereQs(CUSTOMER, ID_CUSTOMER).build();

    @Override
    public Person insert(Person item) throws DaoException {
        return null;
    }

    @Override
    public Person findById(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(Person item) throws DaoException {

    }

    @Override
    public List<Person> getAll() throws DaoException {
        return null;
    }

    @Override
    public void delete(Person item) throws DaoException {

    }

    @Override
    public Person findByBook(Customer customer) throws DaoException {
        return null;
    }
}
