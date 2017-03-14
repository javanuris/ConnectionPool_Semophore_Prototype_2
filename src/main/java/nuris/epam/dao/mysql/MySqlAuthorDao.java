package nuris.epam.dao.mysql;

import nuris.epam.dao.AuthorDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Author;
import nuris.epam.entity.BaseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlAuthorDao extends AuthorDao {
    private static final String AUTHOR = "author";
    private static final String ID_AUTHOR = "id_author";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String MIDDLE_NAME = "middle_name";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(AUTHOR).whereQs(ID_AUTHOR).build();
    private static final String INSERT = Sql.create().insert().var(AUTHOR).values(ID_AUTHOR, 3).build();
    private static final String UPDATE = Sql.create().update().var(AUTHOR).set().varQs(FIRST_NAME).c().varQs(LAST_NAME).c().varQs(MIDDLE_NAME).whereQs(ID_AUTHOR).build();
    private static final String DELETE = Sql.create().delete().var(AUTHOR).whereQs(ID_AUTHOR).build();
    private static final String SELECT_ALL = Sql.create().select().allFrom().var(AUTHOR).build();


    @Override
    public Author insert(Author item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT)) {
                statement(statement, item, 0, 1, 2, 3);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by entity from AuthorDAO" + item, e);
        }
        return item;
    }

    @Override
    public Author findById(int id) throws DaoException {
        Author author = new Author();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        author = item(author, resultSet);
                    }
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Can not insert by id from AuthorDAO " + id, e);
        }
        return author;
    }

    @Override
    public void update(Author item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statement(statement, item, 4, 1, 2, 3).executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Can not update by entity from AuthorDAO " + item, e);
        }
    }

    @Override
    public List<Author> getAll() throws DaoException {
        List<Author> list = new ArrayList<>();
        Author author = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(item(author, resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not get allList from AuthorDAO", e);
        }
        return list;
    }

    @Override
    public void delete(Author item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Can not delete by entity from AuthorDAO " + item, e);
        }
    }

    private PreparedStatement statement(PreparedStatement statement, Author item, int id, int fisrtN, int lastN, int middleN) throws SQLException {
        if (id > 0) {
            statement.setInt(id, item.getId());
            statement.setString(fisrtN, item.getFirstName());
            statement.setString(lastN, item.getLastName());
            statement.setString(middleN, item.getMiddle_name());
        } else {
            statement.setString(fisrtN, item.getFirstName());
            statement.setString(lastN, item.getLastName());
            statement.setString(middleN, item.getMiddle_name());
        }

        return statement;
    }

    private Author item(Author author, ResultSet resultSet) throws SQLException {
        author = new Author();
        author.setId(resultSet.getInt(1));
        author.setFirstName(resultSet.getString(2));
        author.setLastName(resultSet.getString(3));
        author.setMiddle_name(resultSet.getString(4));
        return author;
    }

}
