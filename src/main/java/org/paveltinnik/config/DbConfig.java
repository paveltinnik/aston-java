package org.paveltinnik.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {
    private Properties properties = new Properties();public DbConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUrl() {
        return properties.getProperty("jdbc.url");
    }

    public String getUser() {
        return properties.getProperty("jdbc.user");
    }

    public String getPassword() {
        return properties.getProperty("jdbc.password");
    }
}