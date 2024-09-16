package org.paveltinnik.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.paveltinnik.dto.GenreDTO;
import org.paveltinnik.service.GenreService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class GenreServlet extends HttpServlet {
    private GenreService genreService;

    public GenreServlet(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        Long id = Long.parseLong(path);
        GenreDTO genreDTO = genreService.getGenreById(id);
        if (genreDTO != null) {
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), genreDTO);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (InputStream body = request.getInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            GenreDTO genreDTO = mapper.readValue(body, GenreDTO.class);
            genreDTO = genreService.createGenre(genreDTO);
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), genreDTO);
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
            GenreDTO genreDTO = mapper.readValue(body, GenreDTO.class);
            genreDTO.setId(id);
            genreDTO = genreService.updateGenre(genreDTO);
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), genreDTO);
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
        genreService.deleteGenre(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}