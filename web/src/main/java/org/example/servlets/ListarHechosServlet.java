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

@WebServlet(name = "ListarHechos", value = "/listar-hechos")
public class ListarHechosServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
	@EJB
    private verificandoUyLocal verificandoUy;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HechosModel> hechos = verificandoUy.getHechos();
        request.setAttribute("hechos", hechos);
        request.getRequestDispatcher("/listar.jsp").forward(request, response);
    }
}
