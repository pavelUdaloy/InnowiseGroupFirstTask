package by.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.util.TextLabels.*;


public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_NAME);
            Connection connection = DriverManager.getConnection(URL_FOR_DB, USERNAME_FOR_DB, PASSWORD_FOR_DB);
            System.out.println();
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
