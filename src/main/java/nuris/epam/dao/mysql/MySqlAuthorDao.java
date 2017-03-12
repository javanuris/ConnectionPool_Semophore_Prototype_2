package nuris.epam.dao.mysql;

import nuris.epam.dao.AuthorDao;
import nuris.epam.entity.Author;
import nuris.epam.entity.BaseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySqlAuthorDao extends AuthorDao{
    private static final String FIND_BY_ID = "select * from author where id_author = ?";
    private static final String INSERT ="insert into author values(id_author ,?, ?, ?)";
    private static final String UPDATE ="update author set first_name = ? , last_name =? ,middle_name=? where id_author= ?";
    private static final String DELETE = "delete from author where id_author = ?";
    private static final String SELECT_ALL = "select * from author";
    @Override
    public Author insert(Author item) {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT)) {
                statement.setString(1, item.getFirstName());
                statement.setString(2, item.getLastName());
                statement.setString(3, item.getMiddle_name());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Author findById(int id) {
        Author author = new Author();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)
            ) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        author.setId(resultSet.getInt(1));
                        author.setFirstName(resultSet.getString(2));
                        author.setLastName(resultSet.getString(3));
                        author.setMiddle_name(resultSet.getString(4));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public void update(Author item) {
        try {
            try(PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statement.setString(1 , item.getFirstName());
                statement.setString(2 , item.getLastName());
                statement.setString(3 , item.getMiddle_name());
                statement.setInt(4 , item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public void delete(Author item) {
        try {
            try(PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1 , item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
