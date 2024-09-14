package org.paveltinnik.servlet;

import com.google.gson.Gson;
import org.paveltinnik.dto.BookDTO;
import org.paveltinnik.service.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class BookServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init() {
        bookService = new BookService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        BookDTO book = bookService.getBookById(id);
        if (book != null) {
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(book));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // Другие методы doPost, doPut, doDelete
}
