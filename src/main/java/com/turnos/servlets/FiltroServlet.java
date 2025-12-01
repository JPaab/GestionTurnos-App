package com.turnos.servlets;

import com.turnos.entities.Ciudadano;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("filtro")
public class FiltroServlet extends HttpServlet {

    private List<Ciudadano> ciudadanos = new ArrayList<>();


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String dni = req.getParameter("dni");
        String email = req.getParameter("email");

        Ciudadano c = new Ciudadano(nombre, apellido, dni, email);

        //Agrega al ciudadano a la lista
        ciudadanos.add(c);

        resp.sendRedirect("ciudadano");


    }
}
