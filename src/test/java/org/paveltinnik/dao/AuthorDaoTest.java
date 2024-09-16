package org.paveltinnik.dao;

import org.junit.jupiter.api.Test;
import org.paveltinnik.model.Author;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class AuthorDaoTest {
//    @Container
//    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
//            .withDatabaseName("work2")
//            .withUsername("postgres")
//            .withPassword("admin");
//
//    @Test
//    public void testFindById() {
//        // Запускаем PostgreSQL контейнер
//        postgres.start();
//
//        // Получаем подключение к базе данных
//        String jdbcUrl = postgres.getJdbcUrl();
//        String username = postgres.getUsername();
//        String password = postgres.getPassword();
//
//        AuthorDao authorDao = new AuthorDao(jdbcUrl, username, password);
//
//        Author author = new Author(1L, "Test Author");
//        // ... сохраните Author в базу данных
//        Author foundAuthor = authorDao.findById(1L);
//        assertEquals("Test Author", foundAuthor.getName());
//    }
}