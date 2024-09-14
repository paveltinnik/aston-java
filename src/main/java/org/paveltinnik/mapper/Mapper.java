package org.paveltinnik.mapper;

import org.mapstruct.factory.Mappers;
import org.paveltinnik.Author;
import org.paveltinnik.Book;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.dto.BookDTO;

@org.mapstruct.Mapper
public interface Mapper {
    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    AuthorDTO toAuthorDTO(Author author);

    BookDTO toBookDTO(Book book);

    Author toAuthor(AuthorDTO authorDTO);

    Book toBook(BookDTO bookDTO);
}