package com.turnos.servlets;

import com.turnos.entities.Ciudadano;
import com.turnos.entities.Turno;
import com.turnos.persistence.CiudadanoRepositoryJPA;
import com.turnos.persistence.TurnoRepositoryJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@WebServlet("/filtro")
public class FiltroServlet extends HttpServlet {

    private TurnoRepositoryJPA turnoRepositoryJPA = new TurnoRepositoryJPA();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("jsp/filtrar-turnos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String estadoTurno = req.getParameter("estado");
            String fechaTurno = req.getParameter("fecha");

            List<Turno> todosLosTurnos = turnoRepositoryJPA.encontrarTodos();
            List<Turno> turnosFiltrados = todosLosTurnos.stream()
                    .filter(turno -> {
                        if("todos".equals(estadoTurno)){
                                return true;
                            }

                            return turno.getEstado()
                                    .name()
                                    .equals(estadoTurno);
                        })
                        .filter(turno ->{
                            if (fechaTurno == null || fechaTurno.trim().isEmpty()){
                                return true;
                            }
                            LocalDate fechaDelTurno = turno.getFecha().toLocalDate();
                            LocalDate fechaFiltro = LocalDate.parse(fechaTurno);
                            return fechaDelTurno.equals(fechaFiltro);
                        })
                    .sorted((t1, t2)-> t1.getIdentificadorProgresivo().compareTo(t2.getIdentificadorProgresivo()))
                    .toList();
                        req.setAttribute("turnos", turnosFiltrados);
                        req.setAttribute("totalFiltrados", turnosFiltrados.size());
                        req.setAttribute("turnosOriginales", todosLosTurnos);
                        req.getRequestDispatcher("jsp/listar-turnos.jsp")
                                .forward(req, resp);
            }catch (Exception e){
                    req.setAttribute("error", "- Fallo en el filtro." + e.getMessage());
                    req.getRequestDispatcher("jsp/filtrar-turnos.jsp")
                            .forward(req, resp);
            }

        }
    }
