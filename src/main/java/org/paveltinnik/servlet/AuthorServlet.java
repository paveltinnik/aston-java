package org.paveltinnik.servlet;

import com.google.gson.Gson;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.service.AuthorService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthorServlet extends HttpServlet {
    private AuthorService authorService;

    @Override
    public void init() {
        authorService = new AuthorService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        AuthorDTO author = authorService.getAuthorById(id);
        if (author != null) {
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(author));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // Другие методы doPost, doPut, doDelete
}
