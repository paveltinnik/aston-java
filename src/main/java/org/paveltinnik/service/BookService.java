package org.paveltinnik.service;

import org.paveltinnik.Book;
import org.paveltinnik.dao.BookDao;
import org.paveltinnik.dto.BookDTO;
import org.paveltinnik.mapper.Mapper;

public class BookService {
    private BookDao bookDao;

    public BookService() {
        bookDao = new BookDao();
    }

    public BookDTO getBookById(Long id) {
        Book book = bookDao.findById(id);
        if (book != null) {
            return Mapper.INSTANCE.toBookDTO(book);
        }
        return null;
    }

    // Другие методы для CRUD операций
}