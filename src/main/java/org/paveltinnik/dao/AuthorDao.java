package org.paveltinnik.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.paveltinnik.Author;
import org.paveltinnik.config.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    private HikariDataSource dataSource;

    public AuthorDao() {
        HikariConfig config = new HikariConfig();
        DbConfig dbConfig = new DbConfig();
        config.setJdbcUrl(dbConfig.getUrl());
        config.setUsername(dbConfig.getUser());
        config.setPassword(dbConfig.getPassword());
        dataSource = new HikariDataSource(config);
    }

    public Author findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Author author = new Author(resultSet.getLong("id"), resultSet.getString("name"));
                return author;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Другие CRUD операции для Author
}
