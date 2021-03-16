package by.db;

import by.exception.ConnectionWithDBLostException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.util.TextLabels.PROPERTIES_DB_DRIVER;
import static by.util.TextLabels.PROPERTIES_DB_PASSWORD;
import static by.util.TextLabels.PROPERTIES_DB_URL;
import static by.util.TextLabels.PROPERTIES_DB_USERNAME;
import static by.util.TextLabels.property;

public class ConnectionFactory {
    public static Connection getConnection() throws ConnectionWithDBLostException {
        try {
            Class.forName(property.getProperty(PROPERTIES_DB_DRIVER));
            Connection connection = DriverManager.getConnection(property.getProperty(PROPERTIES_DB_URL),
                    property.getProperty(PROPERTIES_DB_USERNAME),
                    property.getProperty(PROPERTIES_DB_PASSWORD));
            connection.setAutoCommit(false);
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionWithDBLostException(e.toString());
        }
    }
}
