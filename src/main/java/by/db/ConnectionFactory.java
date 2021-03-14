package by.db;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static by.util.TextLabels.PROPERTIES_DB_PASSWORD;
import static by.util.TextLabels.PROPERTIES_DB_URL;
import static by.util.TextLabels.PROPERTIES_DB_USERNAME;
import static by.util.TextLabels.PROPERTIES_PATH;


public class ConnectionFactory {
    private static final Properties property = new Properties();

    public static Connection getConnection() {
        try {
            FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(property.getProperty(PROPERTIES_DB_URL),
                    property.getProperty(PROPERTIES_DB_USERNAME),
                    property.getProperty(PROPERTIES_DB_PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
