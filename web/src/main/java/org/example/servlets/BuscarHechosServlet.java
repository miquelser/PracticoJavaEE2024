package org.example.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.verificandoUyLocal;
import org.example.models.HechosModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BuscarHechos", value = "/buscar-hechos")
public class BuscarHechosServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	@EJB
    private verificandoUyLocal verificandoUy;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        String buscar = request.getParameter("buscar");
        List<HechosModel> hechos = verificandoUy.buscarHechos(tipo, buscar);
        request.setAttribute("hechos", hechos);
        request.setAttribute("resultado", "ok");

        request.getRequestDispatcher("/buscar.jsp").forward(request, response);
    }
}
