package com.ejemplo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ejemplo.model.FanficDAO;
import com.ejemplo.model.StatsDAO;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/estadisticas")
public class EstadisticasServlet extends HttpServlet {

    private static final int MINIMO_PARA_STATS = 10;

    private final Gson gson = new Gson();
    private final FanficDAO fanficDAO = new FanficDAO();
    private final StatsDAO statsDAO = new StatsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try {
            int totalFanfics = fanficDAO.contarFanfics();

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("ok", true);
            respuesta.put("totalFanfics", totalFanfics);

            if (totalFanfics < MINIMO_PARA_STATS) {
                respuesta.put("enabled", false);
                respuesta.put("mensaje", "Necesitas al menos 10 fanfics para desbloquear las estadisticas");
                response.getWriter().write(gson.toJson(respuesta));
                return;
            }

            respuesta.put("enabled", true);
            respuesta.put("topRelationships", statsDAO.topRelationships(5));
            respuesta.put("topFandoms", statsDAO.topFandoms(5));
            respuesta.put("topWarnings", statsDAO.topWarnings(5));
            respuesta.put("ao3Ratings", statsDAO.ratingAo3Breakdown());
            respuesta.put("userStars", statsDAO.estrellasBreakdown());
            respuesta.put("categories", statsDAO.categoriesBreakdown());
            respuesta.put("averageWords", statsDAO.mediaPalabras());

            response.getWriter().write(gson.toJson(respuesta));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            Map<String, Object> error = new HashMap<>();
            error.put("ok", false);
            error.put("mensaje", "No se pudieron cargar las estadisticas");
            error.put("detalle", e.getMessage());

            response.getWriter().write(gson.toJson(error));
        }
    }
}
