package org.paveltinnik.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.paveltinnik.dao.AuthorDao;
import org.paveltinnik.dao.BookDao;
import org.paveltinnik.dto.BookDTO;
import org.paveltinnik.service.AuthorService;
import org.paveltinnik.service.BookService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

//@WebServlet("/api/books/*")
public class BookServlet extends HttpServlet {
    private BookService bookService;

    public BookServlet(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        Long id = Long.parseLong(path);
        BookDTO bookDTO = bookService.getBookById(id);
        if (bookDTO != null) {
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), bookDTO);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (InputStream body = request.getInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            BookDTO bookDTO = mapper.readValue(body, BookDTO.class);
            bookDTO = bookService.createBook(bookDTO);
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), bookDTO);
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
            BookDTO bookDTO = mapper.readValue(body, BookDTO.class);
            bookDTO.setId(id);
            bookDTO = bookService.updateBook(bookDTO);
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), bookDTO);
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
        bookService.deleteBook(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}