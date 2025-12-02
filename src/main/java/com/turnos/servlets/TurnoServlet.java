package com.turnos.servlets;

import com.turnos.entities.Ciudadano;
import com.turnos.entities.Turno;
import com.turnos.entities.TurnoEstado;
import com.turnos.persistence.CiudadanoRepositoryJPA;
import com.turnos.persistence.TurnoRepositoryJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet("/turnos")
public class TurnoServlet extends HttpServlet {

    private TurnoRepositoryJPA turnoRepo = new TurnoRepositoryJPA();
    private CiudadanoRepositoryJPA ciudadanoRepo = new CiudadanoRepositoryJPA();

    //Enviar lista de turnos al JSP
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("nuevo".equals(accion)) {
            List<Ciudadano> ciudadanos = ciudadanoRepo.encontrarTodos();
            req.setAttribute("ciudadanos", ciudadanos);
            req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);
        } else {
            List<Turno> turnos = turnoRepo.encontrarTodos();
            req.setAttribute("turnos", turnos);
            req.getRequestDispatcher("jsp/listar-turnos.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String descripcion = req.getParameter("descripcionTramite");
            String dni = req.getParameter("dniCiudadano");


            if (descripcion == null || descripcion.trim().isEmpty() ||
                    dni == null || dni.trim().isEmpty()) {

                req.setAttribute("error", "Todos los campos son obligatorios");
                req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);
                return;

            }

            Ciudadano ciudadano = ciudadanoRepo.buscarPorDni(dni);

            if (ciudadano == null) {
                req.setAttribute("error", "No existe ciudadano con ese DNI");
                req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);
                return;
            }

            String identificador = turnoRepo.obtenerSiguienteIdentificador();
            Turno turno = new Turno(identificador, TurnoEstado.EN_ESPERA, LocalDateTime.now(), descripcion, ciudadano);
            turnoRepo.guardar(turno);
            resp.sendRedirect("turnos?success=created");


        } catch (Exception e) {

            req.setAttribute("error", "fallo en turno: " + e.getMessage());
            req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);

        }
    }

}
