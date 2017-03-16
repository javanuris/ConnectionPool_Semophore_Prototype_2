package nuris.epam.dao.mysql;

import nuris.epam.dao.BookDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15.03.2017.
 */
public class MySqlBook extends BookDao {
    private static final String BOOK = "book";
    private static final String ID_BOOK = "id_book";
    private static final String NAME = "name";
    private static final String YEAR = "year";
    private static final String ISBN = "isbn";
    private static final String ID_AUTHOR = "id_author";
    private static final String ID_PUBLISHER = "id_publisher";
    private static final String ID_GENRE = "id_genre";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(BOOK).whereQs(ID_BOOK).build();
    private static final String INSERT = Sql.create().insert().var(BOOK).values(ID_BOOK, 6).build();
    private static final String UPDATE = Sql.create().update().var(BOOK).set().varQs(NAME).c().varQs(YEAR).c().varQs(ISBN).c().varQs(ID_GENRE).c().varQs(ID_AUTHOR).c().varQs(ID_PUBLISHER).whereQs(ID_BOOK).build();
    private static final String DELETE = Sql.create().delete().var(BOOK).whereQs(ID_BOOK).build();
    private static final String SELECT_ALL = Sql.create().select().allFrom().var(BOOK).build();

    @Override
    public Book insert(Book item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement(statement, item);
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
    public Book findById(int id) throws DaoException {
        Book book = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        book = itemBook(book, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by id from " + this.getClass().getSimpleName(), e);
        }
        return book;
    }

    @Override
    public void update(Book item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statement(statement, item);
                statement.setInt(7, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Can not update by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public List<Book> getAll() throws DaoException {
        List<Book> list = new ArrayList<>();
        Book book = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        book = itemBook(book, resultSet);
                        list.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not get allList from " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public void delete(Book item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot delete by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    private PreparedStatement statement(PreparedStatement statement, Book item) throws SQLException {
        statement.setString(1, item.getName());
        statement.setDate(2, item.getDate());
        statement.setInt(3, item.getIsbn());
        statement.setInt(4, item.getGenre().getId());
        statement.setInt(5, item.getAuthor().getId());
        statement.setInt(6, item.getPublisher().getId());
        return statement;
    }


    private Book itemBook(Book book, ResultSet resultSet) throws SQLException {
        book = new Book();
        book.setId(resultSet.getInt(1));
        book.setName(resultSet.getString(2));
        book.setDate(resultSet.getDate(3));
        return book;
    }
}
