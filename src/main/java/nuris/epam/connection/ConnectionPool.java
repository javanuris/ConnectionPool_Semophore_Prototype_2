package nuris.epam.connection;

import nuris.epam.dao.exception.NoDBPropertiesException;
import nuris.epam.dao.exception.ResourcesException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by User on 09.03.2017.
 */
public class ConnectionPool {
    private String user;
    private String password;
    private String url;
    private String type;
    private static ConnectionPool connectionPool;
    private final static int CONNECTION_POOL_SIZE = 5;
    private ResourcesQueue<Connection> connections = null;

    private ConnectionPool() {
        init();
    }

    private void init() {
        loadProperties();
        connections = new ResourcesQueue<Connection>(CONNECTION_POOL_SIZE);
        try {
            while (connections.size() < CONNECTION_POOL_SIZE) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connections.addResource(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(ConnectionPool.class.getResourceAsStream("/db.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            type = properties.getProperty("type");
        } catch (IOException e) {
            throw new NoDBPropertiesException("Can't read file", e);
        }

    }

    public Connection getConnection() {
        try {
            return connections.takeResource();
        } catch (ResourcesException e) {
            throw new RuntimeException("Error in a getConnection() , don't avalible connect", e);
        }

    }
    public void returnConnection(Connection connection) {
        connections.returnResource(connection);
        System.out.println("return connect");
    }

    public static ConnectionPool getInstance() {
        if (null == connectionPool) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public int size() {
        return connections.size();
    }

    public String getType() {
        return type;
    }
}
