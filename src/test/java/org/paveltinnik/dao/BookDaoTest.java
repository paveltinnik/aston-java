package org.paveltinnik.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.paveltinnik.Author;
import org.paveltinnik.Book;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class BookDaoTest {
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("work2")
            .withUsername("postgres")
            .withPassword("admin");

    @Test
    public void testFindById() {
        // Запускаем PostgreSQL контейнер
        postgres.start();

        // Получаем подключение к базе данных
        String jdbcUrl = postgres.getJdbcUrl();
        String username = postgres.getUsername();
        String password = postgres.getPassword();

        BookDao bookDao = new BookDao(jdbcUrl, username, password);

        Author author = new Author(1L, "Test Author");
        Book book = new Book(1L, "Test Book", author);
        // ... сохраните Book в базу данных
        Book foundBook = bookDao.findById(1L);
        assertEquals("Test Book", foundBook.getTitle());
    }
}