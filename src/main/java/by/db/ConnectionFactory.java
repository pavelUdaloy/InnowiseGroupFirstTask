package by.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.util.TextLabels.*;


public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL_FOR_DB, USERNAME_FOR_DB, PASSWORD_FOR_DB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
