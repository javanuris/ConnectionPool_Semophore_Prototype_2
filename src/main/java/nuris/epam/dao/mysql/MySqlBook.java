package nuris.epam.dao.mysql;

import nuris.epam.dao.BookDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Author;
import nuris.epam.entity.Book;
import nuris.epam.entity.Genre;
import nuris.epam.entity.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 15.03.2017.
 */
public class MySqlBook extends BookDao {
    private static final String BOOK = "book";
    private static final String ID_BOOK = "id_book";
    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(BOOK).whereQs(ID_BOOK).build();
    //  private static final String INSERT = Sql.create().insert().var(AUTHOR).values(ID_AUTHOR, 3).build();
    //  private static final String UPDATE = Sql.create().update().var(AUTHOR).set().varQs(FIRST_NAME).c().varQs(LAST_NAME).c().varQs(MIDDLE_NAME).whereQs(ID_AUTHOR).build();
    //  private static final String DELETE = Sql.create().delete().var(AUTHOR).whereQs(ID_AUTHOR).build();
    //private static final String SELECT_ALL = Sql.create().select().allFrom().var(AUTHOR).build();
    @Override
    public Book insert(Book item) throws DaoException {
        return null;
    }

    @Override
    public Book findById(int id) throws DaoException {
        Book book = new Book();
        Author author = new Author();
        Genre genre = new Genre();
        Publisher publisher = new Publisher();

        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        book.setId(resultSet.getInt(1));
                        book.setName(resultSet.getString(2));
                        book.setDate(resultSet.getDate(3));
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

    }

    @Override
    public List<Book> getAll() throws DaoException {
        return null;
    }

    @Override
    public void delete(Book item) throws DaoException {

    }
}
