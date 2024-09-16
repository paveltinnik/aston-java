package org.paveltinnik.service;

import org.paveltinnik.exception.ResourceNotFoundException;
import org.paveltinnik.model.Author;
import org.paveltinnik.dao.AuthorDao;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.mapper.CustomMapper;

public class AuthorService {
    private AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorDao.findById(id);
        if (author != null) {
            return new AuthorDTO(author.getId(), author.getName());
        }
        return null;
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author(null, authorDTO.getName());
        author = authorDao.create(author);
        return new AuthorDTO(author.getId(), author.getName());
    }

    public AuthorDTO updateAuthor(AuthorDTO authorDTO) {
        Author author = new Author(authorDTO.getId(), authorDTO.getName());
        author = authorDao.update(author);
        return new AuthorDTO(author.getId(), author.getName());
    }

    public void deleteAuthor(Long id) {
        authorDao.delete(id);
    }
}