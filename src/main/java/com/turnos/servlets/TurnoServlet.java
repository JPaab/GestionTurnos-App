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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//aqui servlet principal para listar turnos y crear nuevos turnos
@WebServlet("/turnos")
public class TurnoServlet extends HttpServlet {

    //aqui repositorio de turnos
    private TurnoRepositoryJPA turnoRepo = new TurnoRepositoryJPA();

    //aqui repositorio de ciudadanos para mostrar en el combo al crear turno
    private CiudadanoRepositoryJPA ciudadanoRepo = new CiudadanoRepositoryJPA();

    //aqui controlo si voy al formulario nuevo turno o al listado general
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        //aqui si action=nuevo cargo ciudadanos y voy al JSP de crear turno
        if ("nuevo".equals(action)) {
            List<Ciudadano> ciudadanos = ciudadanoRepo.encontrarTodos();
            req.setAttribute("ciudadanos", ciudadanos);
            req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);
        } else {
            //aqui si no hay action muestro todos los turnos en la tabla
            List<Turno> turnos = turnoRepo.encontrarTodos();
            req.setAttribute("turnos", turnos);
            req.getRequestDispatcher("jsp/listar-turnos.jsp").forward(req, resp);
        }
    }

    //aqui proceso el formulario para crear un nuevo turno: leo datos, valido, creo y guardo
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String descripcion = req.getParameter("descripcionTramite");
            String dni = req.getParameter("dniCiudadano");
            String fechaStr = req.getParameter("fechaTurno");


            if (descripcion == null || descripcion.trim().isEmpty() ||
                    dni == null || dni.trim().isEmpty() ||
                    fechaStr == null || fechaStr.trim().isEmpty()) {

                req.setAttribute("error", "- Todos los campos son obligatorios");
                req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);
                return;

            }
            //aqui convierto el texto de la fecha a LocalDate
            LocalDate fechaFormulario = LocalDate.parse(fechaStr);
            LocalDate hoy = LocalDate.now();

            //aqui no dejo crear turnos con fecha anterior a hoy
            if (fechaFormulario.isBefore(hoy)) {
                req.setAttribute("error", "- La fecha del turno no puede ser anterior a hoy");
                req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);
                return;
            }

            //aqui preparo la fecha completa con hora 00:00 para guardar en la entidad
            LocalDateTime fechaTurno = fechaFormulario.atStartOfDay();

            Ciudadano ciudadano = ciudadanoRepo.buscarPorDni(dni);

            if (ciudadano == null) {
                req.setAttribute("error", "- No existe ciudadano con ese DNI");
                req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);
                return;
            }

            String identificador = turnoRepo.obtenerSiguienteIdentificador();
            Turno turno = new Turno(identificador, TurnoEstado.EN_ESPERA, fechaTurno, descripcion, ciudadano);
            //aqui guardo el turno en la BD y vuelvo al listado
            turnoRepo.guardar(turno);
            resp.sendRedirect("turnos?success=created");


        } catch (Exception e) {

            //aqui si algo falla al crear el turno muestro un error generico en el formulario
            req.setAttribute("error", "- Fallo en turno. " + e.getMessage());
            req.getRequestDispatcher("jsp/agregar-turno.jsp").forward(req, resp);

        }
    }

}
