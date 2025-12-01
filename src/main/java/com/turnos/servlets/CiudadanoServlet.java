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

@WebServlet("/listas")
public class CiudadanoServlet extends HttpServlet {

    private List<Ciudadano> ciudadanos = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Envia la lista de ciudadanos al JSP
        req.setAttribute("listarCiudadanos", ciudadanos);

        //Muestra la tabla de los ciudadanos
        req.getRequestDispatcher("jsp/listar-ciudadanos.jsp")
                .forward(req, resp);


    }
}
