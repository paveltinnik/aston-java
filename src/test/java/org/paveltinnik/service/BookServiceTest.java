package org.paveltinnik.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.paveltinnik.Author;
import org.paveltinnik.Book;
import org.paveltinnik.dao.BookDao;
import org.paveltinnik.dto.BookDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceTest {
    @Test
    public void testGetBookById() {
        BookDao bookDao = Mockito.mock(BookDao.class);
        BookService bookService = new BookService(bookDao);
        Author author = new Author(1L, "Test Author");
        Book book = new Book(1L, "Test Book", author);
        Mockito.when(bookDao.findById(1L)).thenReturn(book);

        BookDTO bookDTO = bookService.getBookById(1L);
        assertEquals("Test Book", bookDTO.getTitle());
    }
}