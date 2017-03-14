package nuris.epam.dao.mysql;

import nuris.epam.dao.GenreDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Genre;
import nuris.epam.entity.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.03.2017.
 */
public class MySqlGenreDao extends GenreDao {
    public static final String GENRE = "genre";
    public static final String NAME = "name";
    public static final String ID_GENRE = "id_genre";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(GENRE).whereQs(ID_GENRE).build();
    private static final String INSERT = Sql.create().insert().var(GENRE).values(ID_GENRE, 1).build();
    private static final String UPDATE = Sql.create().update().var(GENRE).set().varQs(NAME).whereQs(ID_GENRE).build();
    private static final String DELETE = Sql.create().delete().var(GENRE).whereQs(ID_GENRE).build();
    private static final String SELECT_ALL = Sql.create().select().allFrom().var(GENRE).build();

    @Override
    public Genre insert(Genre item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT)) {
                statement.setString(1, item.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    @Override
    public Genre findById(int id) throws DaoException {
        Genre genre = new Genre();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        genre.setId(resultSet.getInt(1));
                        genre.setName(resultSet.getString(2));

                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by id from " + this.getClass().getSimpleName(), e);
        }
        return genre;
    }

    @Override
    public void update(Genre item) throws DaoException {
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
    public List<Genre> getAll() throws DaoException {
        List<Genre> list = new ArrayList<>();
        Genre genre = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        genre = new Genre();
                        genre.setId(resultSet.getInt(1));
                        genre.setName(resultSet.getString(2));
                        list.add(genre);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not get allList from " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public void delete(Genre item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot delete by entity from " + this.getClass().getSimpleName() + "|" + item, e);
        }
    }
}
