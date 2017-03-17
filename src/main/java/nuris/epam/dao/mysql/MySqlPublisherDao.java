package nuris.epam.dao.mysql;

import nuris.epam.dao.PublisherDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.03.2017.
 */
public class MySqlPublisherDao extends PublisherDao {
    public static final String PUBLISHER = "publisher";
    public static final String ID_PUBLISHER = "id_publisher";
    public static final String NAME = "name";
    public static final String CITY = "city";
    private static final String BOOK = "book";
    public static final String ID_BOOK = "id_book";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(PUBLISHER).whereQs(ID_PUBLISHER).build();
    private static final String INSERT = Sql.create().insert().var(PUBLISHER).values(ID_PUBLISHER, 2).build();
    private static final String UPDATE = Sql.create().update().var(PUBLISHER).set().varQs(NAME).c().varQs(CITY).whereQs(ID_PUBLISHER).build();
    private static final String DELETE = Sql.create().delete().var(PUBLISHER).whereQs(ID_PUBLISHER).build();
    private static final String SELECT_ALL = Sql.create().select().allFrom().var(PUBLISHER).build();
    public static final String FIND_BY_BOOK = Sql.create().select().varS(PUBLISHER, ID_PUBLISHER).c().varS(PUBLISHER, NAME).c().varS(PUBLISHER, CITY).from().var(PUBLISHER).join(BOOK).varS(BOOK, ID_PUBLISHER).eq().varS(PUBLISHER, ID_PUBLISHER).whereQs(BOOK, ID_BOOK).build();

    @Override
    public Publisher insert(Publisher item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, item.getName());
                statement.setString(2, item.getCity());
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
    public Publisher findById(int id) throws DaoException {
        Publisher publisher = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        publisher = itemPublisher(publisher, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by id from " + this.getClass().getSimpleName(), e);
        }
        return publisher;
    }

    @Override
    public void update(Publisher item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statement.setString(1, item.getName());
                statement.setString(2, item.getCity());
                statement.setInt(3, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Can not update by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public List<Publisher> getAll() throws DaoException {
        List<Publisher> list = new ArrayList<>();
        Publisher publisher = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        publisher = itemPublisher(publisher, resultSet);
                        list.add(publisher);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not get allList from " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public void delete(Publisher item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot delete by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public Publisher findByBook(Book book) throws DaoException {
        Publisher publisher = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_BOOK)) {
                statement.setInt(1, book.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        publisher = itemPublisher(publisher, resultSet);

                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by Book from " + this.getClass().getSimpleName(), e);
        }
        return publisher;
    }

    private Publisher itemPublisher(Publisher publisher, ResultSet resultSet) throws SQLException {
        publisher = new Publisher();
        publisher.setId(resultSet.getInt(1));
        publisher.setName(resultSet.getString(2));
        publisher.setCity(resultSet.getString(3));
        return publisher;
    }

}
