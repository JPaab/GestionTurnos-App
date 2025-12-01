package com.turnos.servlets;

import com.turnos.entities.Ciudadano;
import com.turnos.entities.Turno;
import com.turnos.entities.TurnoEstado;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listaTurnos")
public class TurnoServlet extends HttpServlet {

    private List<Turno> turnos = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listarTurnos", turnos);
        req.getRequestDispatcher("jsp/listar-ciudadanos.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProgresivo = req.getParameter("idProgresivo");
        TurnoEstado estado = TurnoEstado.valueOf(req.getParameter("estado"));
        LocalDateTime fecha = LocalDateTime.parse(req.getParameter("fecha"));
        String descripcionTramite = req.getParameter("descripcionTramite");
        String dniCiudadano = req.getParameter("dni");
        Ciudadano ciudadano = req.getParameter(ciudadano);

        Turno t = new Turno(idProgresivo, estado, fecha, descripcionTramite, ciudadano);
        resp.sendRedirect("turno");
    }
}
