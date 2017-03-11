package nuris.epam.dao;

import java.sql.Connection;

/**
 * Created by User on 10.03.2017.
 */
public abstract class BaseDao implements Dao {
  private  Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
