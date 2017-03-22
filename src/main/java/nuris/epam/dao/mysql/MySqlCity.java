package nuris.epam.dao.mysql;

import nuris.epam.dao.CityDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.City;
import nuris.epam.entity.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17.03.2017.
 */
public class MySqlCity extends CityDao {
    public static final String CITY = "city";
    public static final String NAME = "name";
    public static final String ID_CITY = "id_city";

    public static final String PERSON = "person";
    public static final String ID_PERSON = "id_person";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(CITY).whereQs(ID_CITY).build();
    private static final String INSERT = Sql.create().insert().var(CITY).values(ID_CITY, 1).build();
    private static final String UPDATE = Sql.create().update().var(CITY).set().varQs(NAME).whereQs(ID_CITY).build();
    private static final String DELETE = Sql.create().delete().var(CITY).whereQs(ID_CITY).build();
    private static final String SELECT_ALL = Sql.create().select().allFrom().var(CITY).build();

    private static final String FIND_BY_PERSON = Sql.create().select().varS(CITY, ID_CITY).c()
            .varS(CITY, NAME).from().var(CITY).join(PERSON).varS(PERSON, ID_CITY).eq()
            .varS(CITY, ID_CITY).whereQs(PERSON, ID_PERSON).build();

    @Override
    public City insert(City item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, item.getName());
                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    @Override
    public City findById(int id) throws DaoException {
        City city = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        city = itemGenre(city, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by id from " + this.getClass().getSimpleName(), e);
        }
        return city;
    }

    @Override
    public void update(City item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statement.setString(1, item.getName());
                statement.setInt(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Can not update by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public List<City> getAll() throws DaoException {
        List<City> list = new ArrayList<>();
        City city = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        city = itemGenre(city, resultSet);
                        list.add(city);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not get allList from " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public void delete(City item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot delete City entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public City findByPerson(Person person) throws DaoException {
        City city = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_PERSON)) {
                statement.setInt(1, person.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        city = itemGenre(city, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by Person from " + this.getClass().getSimpleName(), e);
        }
        return city;
    }

    private City itemGenre(City city, ResultSet resultSet) throws SQLException {
        city = new City();
        city.setId(resultSet.getInt(1));
        city.setName(resultSet.getString(2));
        return city;
    }
}
