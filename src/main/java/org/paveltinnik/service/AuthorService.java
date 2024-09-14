package org.paveltinnik.service;

import org.paveltinnik.Author;
import org.paveltinnik.dao.AuthorDao;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.mapper.Mapper;

public class AuthorService {
    private AuthorDao authorDao;

    public AuthorService() {
        authorDao = new AuthorDao();
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorDao.findById(id);
        if (author != null) {
            return Mapper.INSTANCE.toAuthorDTO(author);
        }
        return null;
    }

    // Другие методы для CRUD операций
}
