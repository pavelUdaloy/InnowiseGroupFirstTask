package by.db;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionFactory {
    private static Properties property = new Properties();
    private static FileInputStream fis = null;

    public static Connection getConnection() {
        try {
            fis = new FileInputStream
                    ("C:\\Users\\user\\IdeaProjects\\SimpleWeb\\src\\main\\resources\\properties.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(property.getProperty("dbUrl"),
                    property.getProperty("dbUsername"), property.getProperty("dbPassword"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
