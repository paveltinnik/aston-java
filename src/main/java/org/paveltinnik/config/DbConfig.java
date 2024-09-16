package org.paveltinnik.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "admin";

    HikariDataSource dataSource;

    public HikariDataSource getDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DB_URL);
            config.setUsername(DB_USER);
            config.setPassword(DB_PASSWORD);
            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }
}