package org.paveltinnik.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorServlet extends HttpServlet {

    private final AuthorService authorService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthorServlet(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            AuthorDTO author = authorService.findById(Long.parseLong(id));
            resp.setContentType("application/json");
            resp.getWriter().write(objectMapper.writeValueAsString(author));
        } else {
            resp.getWriter().write("ID not provided");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDTO authorDTO = objectMapper.readValue(req.getReader(), AuthorDTO.class);
        authorService.save(authorDTO);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDTO authorDTO = objectMapper.readValue(req.getReader(), AuthorDTO.class);
        authorService.update(authorDTO);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            authorService.delete(Long.parseLong(id));
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            resp.getWriter().write("ID not provided");
        }
    }
}