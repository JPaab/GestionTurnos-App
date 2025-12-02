package com.turnos.servlets;

import com.turnos.entities.Turno;
import com.turnos.entities.TurnoEstado;
import com.turnos.persistence.TurnoRepositoryJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/turno/actualizar")
public class ActualizarTurnoServlet extends HttpServlet {

    private final TurnoRepositoryJPA turnoRepositoryJPA = new TurnoRepositoryJPA();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            Turno turno = turnoRepositoryJPA.encontrarPorId(id);
            if (turno != null && turno.getEstado() == TurnoEstado.EN_ESPERA) {
                turno.setEstado(TurnoEstado.ATENDIDO); //FIXME USAR METODO TURNO ENTITIES
                turnoRepositoryJPA.guardar(turno);
            }
        }

        response.sendRedirect(request.getContextPath() + "/turnos");
    }
}

