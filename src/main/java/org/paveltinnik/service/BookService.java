package org.paveltinnik.service;

import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.dto.GenreDTO;
import org.paveltinnik.exception.ResourceNotFoundException;
import org.paveltinnik.model.Author;
import org.paveltinnik.model.Book;
import org.paveltinnik.dao.BookDao;
import org.paveltinnik.dto.BookDTO;
import org.paveltinnik.mapper.CustomMapper;
import org.paveltinnik.model.Genre;

import java.util.HashSet;
import java.util.Set;

public class BookService {
    private BookDao bookDao;
    private AuthorService authorService;

    public BookService(BookDao bookDao, AuthorService authorService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
    }

    public BookDTO getBookById(Long id) {
        Book book = bookDao.findById(id);
        if (book != null) {
            AuthorDTO authorDTO = authorService.getAuthorById(book.getAuthor().getId());
            Set<GenreDTO> genreDTOs = new HashSet<>();
            for (Genre genre : book.getGenres()) {
                genreDTOs.add(new GenreDTO(genre.getId(), genre.getName()));
            }
            return new BookDTO(book.getId(), book.getTitle(), authorDTO, genreDTOs);
        }
        return null;
    }

    public BookDTO createBook(BookDTO bookDTO) {
        AuthorDTO authorDTO = bookDTO.getAuthor();
        Author author = new Author(authorDTO.getId(), authorDTO.getName());
        Book book = new Book(null, bookDTO.getTitle());
        book.setAuthor(author);
        book = bookDao.create(book);
        return new BookDTO(book.getId(), book.getTitle(), authorDTO, null);
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        AuthorDTO authorDTO = bookDTO.getAuthor();
        Author author = new Author(authorDTO.getId(), authorDTO.getName());
        Book book = new Book(bookDTO.getId(), bookDTO.getTitle());
        book.setAuthor(author);
        book = bookDao.update(book);
        return new BookDTO(book.getId(), book.getTitle(), authorDTO, null);
    }

    public void deleteBook(Long id) {
        bookDao.delete(id);
    }
}