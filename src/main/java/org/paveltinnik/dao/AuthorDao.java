package org.paveltinnik.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.paveltinnik.model.Author;
import org.paveltinnik.config.DbConfig;

import java.sql.*;

public class AuthorDao {
    private HikariDataSource dataSource;

    public AuthorDao(HikariDataSource dataSource) {
        this.dataSource = dataSource;
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

    public Author create(Author author) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO authors (name) VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return author;
    }

    public Author update(Author author) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE authors SET name = ? WHERE id = ?")) {
            statement.setString(1, author.getName());
            statement.setLong(2, author.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return author;
    }

    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM authors WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}