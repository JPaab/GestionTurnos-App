package com.turnos.servlets;

import com.turnos.entities.Ciudadano;
import com.turnos.persistence.CiudadanoRepositoryJPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ciudadanos")
public class CiudadanoServlet extends HttpServlet {

    private CiudadanoRepositoryJPA ciudadanoRepo = new CiudadanoRepositoryJPA();

    //Envia la lista de ciudadanos al JSP
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("nuevo".equals(action)){
            req.getRequestDispatcher("jsp/crear-ciudadano.jsp").forward(req, resp);
        }else{
            List <Ciudadano> ciudadanos = ciudadanoRepo.encontrarTodos();
            req.setAttribute("ciudadanos", ciudadanos);
            req.getRequestDispatcher("jsp/listar-ciudadanos.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nombre = req.getParameter("nombre");
            String apellido = req.getParameter("apellido");
            String dni = req.getParameter("dni");
            String email = req.getParameter("email");

            if (nombre == null || nombre.trim().isEmpty() ||
                    apellido == null || apellido.trim().isEmpty() ||
                    dni == null || dni.trim().isEmpty() ||
                    email == null || email.trim().isEmpty()) {

                req.setAttribute("Error: ", "Todos los campos son obligatorios");
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

            resp.sendRedirect("ciudadanos?success=created");
        }catch (IllegalArgumentException e){
            req.setAttribute("Error ", e.getMessage());
            req.setAttribute("nombre", req.getParameter("nombre"));
            req.setAttribute("apellido", req.getParameter("apellido"));
            req.setAttribute("dni", req.getParameter("dni"));
            req.setAttribute("email", req.getParameter("email"));

            req.getRequestDispatcher("jsp/crear-ciudadano.jsp")
                    .forward(req, resp);

        }catch (Exception e){
            req.setAttribute("Error, ", "fallo del servidor: " + e.getMessage());
            req.getRequestDispatcher("jsp/crear-ciudadano.jsp")
                    .forward(req, resp);
        }
    }
}
