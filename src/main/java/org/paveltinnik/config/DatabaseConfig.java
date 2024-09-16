package org.paveltinnik.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private static HikariDataSource dataSource;

//    static {
//        HikariConfig config = new HikariConfig("/db.properties"); // Настройки в файле db.properties
//        config.setMaximumPoolSize(10); // Параметры пула соединений
//        dataSource = new HikariDataSource(config);
//    }
//
//    public static DataSource getDataSource() {
//        return dataSource;
//    }
//
//    public static void shutdown() {
//        if (dataSource != null && !dataSource.isClosed()) {
//            dataSource.close();
//        }
//    }

    static {
        try {
            HikariConfig config = new HikariConfig();
            Properties properties = new Properties();

            // Load database configuration from a properties file
            properties.load(DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties"));
            config.setJdbcUrl(properties.getProperty("jdbc.url"));
            config.setUsername(properties.getProperty("jdbc.username"));
            config.setPassword(properties.getProperty("jdbc.password"));
//            config.setDriverClassName(properties.getProperty("jdbc.driver"));

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize the data source", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}