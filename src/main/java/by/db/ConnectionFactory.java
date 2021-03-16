package by.db;

import by.exception.ConnectionWithDBLostException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static by.util.TextLabels.CACHE_PREP_PROPERTY;
import static by.util.TextLabels.CACHE_PREP_PROPERTY_VALUE;
import static by.util.TextLabels.PREP_CACHE_SIZE_PROPERTY;
import static by.util.TextLabels.PREP_CACHE_SIZE_PROPERTY_VALUE;
import static by.util.TextLabels.PREP_CACHE_SQL_LIMIT_PROPERTY;
import static by.util.TextLabels.PREP_CACHE_SQL_LIMIT_PROPERTY_VALUE;
import static by.util.TextLabels.PROPERTIES_DB_DRIVER;
import static by.util.TextLabels.PROPERTIES_DB_PASSWORD;
import static by.util.TextLabels.PROPERTIES_DB_URL;
import static by.util.TextLabels.PROPERTIES_DB_USERNAME;
import static by.util.TextLabels.VALIDATION_PROPERTY;
import static by.util.TextLabels.VALIDATION_PROPERTY_VALUE;
import static by.util.TextLabels.property;

public class ConnectionFactory {

    private static Connection connection;

    public static Connection getConnection() throws ConnectionWithDBLostException {
        if (connection == null) {
            try {
                Class.forName(property.getProperty(PROPERTIES_DB_DRIVER));
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl(property.getProperty(PROPERTIES_DB_URL));
                config.setUsername(property.getProperty(PROPERTIES_DB_USERNAME));
                config.setPassword(property.getProperty(PROPERTIES_DB_PASSWORD));
                config.setMaximumPoolSize(10);
                config.setAutoCommit(false);
                config.addDataSourceProperty(CACHE_PREP_PROPERTY, CACHE_PREP_PROPERTY_VALUE);
                config.addDataSourceProperty(PREP_CACHE_SIZE_PROPERTY, PREP_CACHE_SIZE_PROPERTY_VALUE);
                config.addDataSourceProperty(PREP_CACHE_SQL_LIMIT_PROPERTY, PREP_CACHE_SQL_LIMIT_PROPERTY_VALUE);
                config.addDataSourceProperty(VALIDATION_PROPERTY, VALIDATION_PROPERTY_VALUE);
                DataSource datasource = new HikariDataSource(config);
                connection = datasource.getConnection();
            } catch (SQLException | ClassNotFoundException e) {
                throw new ConnectionWithDBLostException(e.toString());
            }
        }
        return connection;
    }
}
