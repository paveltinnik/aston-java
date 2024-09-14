package org.paveltinnik.servlet;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.dto.BookDTO;
import org.paveltinnik.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServletTest {
    @Test
    public void testDoGet() throws Exception {
        BookService bookService = Mockito.mock(BookService.class);
        BookServlet bookServlet = new BookServlet(bookService);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        AuthorDTO authorDTO = new AuthorDTO(1L, "Test Author");
        BookDTO bookDTO = new BookDTO(1L, "Test Book", authorDTO);
        when(bookService.getBookById(1L)).thenReturn(bookDTO);

        bookServlet.doGet(request, response);

        verify(response).setContentType("application/json");
        // Проверьте, что данные были отправлены в ответ
    }
}