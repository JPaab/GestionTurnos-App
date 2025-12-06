package com.turnos.servlets;

import com.turnos.entities.Ciudadano;
import com.turnos.persistence.CiudadanoRepositoryJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//aqui servlet para listar y crear ciudadanos
@WebServlet("/ciudadanos")
public class CiudadanoServlet extends HttpServlet {

    //aqui uso el repositorio JPA de ciudadanos
    private CiudadanoRepositoryJPA ciudadanoRepo = new CiudadanoRepositoryJPA();

    //aqui controlo el proceso segun el parametro action
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        //aqui si action=nuevo muestro el formulario para crear ciudadano
        if ("nuevo".equals(action)) {
            req.getRequestDispatcher("jsp/crear-ciudadano.jsp").forward(req, resp);
        } else {
            //aqui si no hay action muestro el listado de ciudadanos
            List<Ciudadano> ciudadanos = ciudadanoRepo.encontrarTodos();
            req.setAttribute("ciudadanos", ciudadanos);
            req.getRequestDispatcher("jsp/listar-ciudadanos.jsp").forward(req, resp);
        }
    }

    //aqui proceso el formulario de alta de ciudadano
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nombre = req.getParameter("nombre");
            String apellido = req.getParameter("apellido");
            String dni = req.getParameter("dni");
            String email = req.getParameter("email");

            //aqui valido que ningun campo del formulario venga vacio y devuelvo el error si falta algo
            if (nombre == null || nombre.trim().isEmpty() ||
                    apellido == null || apellido.trim().isEmpty() ||
                    dni == null || dni.trim().isEmpty() ||
                    email == null || email.trim().isEmpty()) {

                req.setAttribute("error", "- Todos los campos son obligatorios");
                req.setAttribute("nombre", nombre);
                req.setAttribute("apellido", apellido);
                req.setAttribute("dni", dni);
                req.setAttribute("email", email);
                req.getRequestDispatcher("jsp/crear-ciudadano.jsp")
                        .forward(req, resp);
                return;
            }

            Ciudadano ciudadano = new Ciudadano(nombre, apellido, dni, email);
            ciudadanoRepo.guardar(ciudadano);

            //aqui guardo el turno en la BD y vuelvo al listado
            resp.sendRedirect("ciudadanos?success=created");

            //aqui capturo validaciones de la entidad y vuelvo al formulario con el mensaje
        } catch (IllegalArgumentException e) {

            req.setAttribute("error", e.getMessage());
            req.setAttribute("nombre", req.getParameter("nombre"));
            req.setAttribute("apellido", req.getParameter("apellido"));
            req.setAttribute("dni", req.getParameter("dni"));
            req.setAttribute("email", req.getParameter("email"));

            req.getRequestDispatcher("jsp/crear-ciudadano.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            //aqui controlo cualquier error general del servidor
            req.setAttribute("error", "- Fallo del servidor." + e.getMessage());
            req.getRequestDispatcher("jsp/crear-ciudadano.jsp")
                    .forward(req, resp);
        }
    }
}
