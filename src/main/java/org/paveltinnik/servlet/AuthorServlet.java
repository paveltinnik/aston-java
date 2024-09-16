package org.paveltinnik.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.paveltinnik.dto.AuthorDTO;
import org.paveltinnik.service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

//@WebServlet("/api/authors/*")
public class AuthorServlet extends HttpServlet {
    private AuthorService authorService;

    public AuthorServlet(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        Long id = Long.parseLong(path);
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        if (authorDTO != null) {
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), authorDTO);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (InputStream body = request.getInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            AuthorDTO authorDTO = mapper.readValue(body, AuthorDTO.class);
            authorDTO = authorService.createAuthor(authorDTO);
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), authorDTO);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid request");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        Long id = Long.parseLong(path);
        try (InputStream body = request.getInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            AuthorDTO authorDTO = mapper.readValue(body, AuthorDTO.class);
            authorDTO.setId(id);
            authorDTO = authorService.updateAuthor(authorDTO);
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), authorDTO);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid request");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        Long id = Long.parseLong(path);
        authorService.deleteAuthor(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}