package org.paveltinnik.service;

import org.paveltinnik.dao.GenreDao;
import org.paveltinnik.dto.GenreDTO;
import org.paveltinnik.exception.ResourceNotFoundException;
import org.paveltinnik.mapper.CustomMapper;
import org.paveltinnik.model.Genre;

public class GenreService {
    private GenreDao genreDao;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public GenreDTO getGenreById(Long id) {
        Genre genre = genreDao.findById(id);
        if (genre != null) {
            return new GenreDTO(genre.getId(), genre.getName());
        }
        return null;
    }

    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = new Genre(null, genreDTO.getName());
        genre = genreDao.create(genre);
        return new GenreDTO(genre.getId(), genre.getName());
    }

    public GenreDTO updateGenre(GenreDTO genreDTO) {
        Genre genre = new Genre(genreDTO.getId(), genreDTO.getName());
        genre = genreDao.update(genre);
        return new GenreDTO(genre.getId(), genre.getName());
    }

    public void deleteGenre(Long id) {
        genreDao.delete(id);
    }
}