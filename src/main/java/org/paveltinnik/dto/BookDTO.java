package org.paveltinnik.dto;

import java.util.Set;

public class BookDTO {
    private Long id;
    private String title;
    private AuthorDTO author;
    private Set<GenreDTO> genres;

    public BookDTO(Long id, String title, AuthorDTO author, Set<GenreDTO> genres) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDTO> genres) {
        this.genres = genres;
    }
}