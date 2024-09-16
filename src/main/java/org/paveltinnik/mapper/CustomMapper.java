package org.paveltinnik.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.paveltinnik.dto.GenreDTO;
import org.paveltinnik.model.Author;
import org.paveltinnik.model.Book;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.dto.BookDTO;
import org.paveltinnik.model.Genre;

@Mapper
public interface CustomMapper {
    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    AuthorDTO toAuthorDTO(Author author);
    BookDTO toBookDTO(Book book);
    GenreDTO toGenreDTO(Genre genre);

    Author toAuthor(AuthorDTO authorDTO);
    Book toBook(BookDTO bookDTO);
    Genre toGenre(GenreDTO genreDTO);
}