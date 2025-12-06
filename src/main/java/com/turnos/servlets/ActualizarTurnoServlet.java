package com.turnos.servlets;

import com.turnos.entities.Turno;
import com.turnos.persistence.TurnoRepositoryJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//aqui servlet para pasar un turno de EN_ESPERA a ATENDIDO
@WebServlet("/turnos/actualizar")
public class ActualizarTurnoServlet extends HttpServlet {

    //aqui uso el repositorio de turnos para leer y guardar en la BD
    private final TurnoRepositoryJPA turnoRepositoryJPA = new TurnoRepositoryJPA();

    //aqui recibo el id del turno y actualizo su estado si corresponde
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //aqui obtengo el id enviado desde el formulario del JSP
        String idParam = request.getParameter("id");

        //aqui busco el turno por id y solo lo marco como atendido si estaba en espera
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            Turno turno = turnoRepositoryJPA.encontrarPorId(id);
            if (turno != null && turno.estaEnEspera()) {
                turno.marcarComoAtendido();
                turnoRepositoryJPA.guardar(turno);
            }
        }

        //aqui vuelvo al listado general de turnos despues de actualizar
        response.sendRedirect(request.getContextPath() + "/turnos");
    }
}

