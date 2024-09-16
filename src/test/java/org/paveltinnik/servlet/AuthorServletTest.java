package org.paveltinnik.servlet;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.service.AuthorService;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class AuthorServletTest {
//    @Test
//    public void testDoGet() throws Exception {
//        AuthorService authorService = Mockito.mock(AuthorService.class);
//        AuthorServlet authorServlet = new AuthorServlet(authorService);
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//
//        when(request.getParameter("id")).thenReturn("1");
//        AuthorDTO authorDTO = new AuthorDTO(1L, "Test Author");
//        when(authorService.getAuthorById(1L)).thenReturn(authorDTO);
//
//        authorServlet.doGet(request, response);
//
//        verify(response).setContentType("application/json");
//        // Проверьте, что данные были отправлены в ответ
//    }
}