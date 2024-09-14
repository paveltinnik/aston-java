package org.paveltinnik.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.paveltinnik.Book;
import org.paveltinnik.config.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private HikariDataSource dataSource;

    public BookDao() {
        HikariConfig config = new HikariConfig();
        DbConfig dbConfig = new DbConfig();
        config.setJdbcUrl(dbConfig.getUrl());
        config.setUsername(dbConfig.getUser());
        config.setPassword(dbConfig.getPassword());
        dataSource = new HikariDataSource(config);
    }

    public Book findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        new AuthorDao().findById(resultSet.getLong("author_id")));
                return book;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Другие CRUD операции для Book
}
