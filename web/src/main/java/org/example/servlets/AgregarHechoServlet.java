package org.example.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.verificandoUyLocal;
import org.example.models.Calificacion;
import org.example.models.Estado;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AgregarHecho", value = "/agregar-hecho")
public class AgregarHechoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private verificandoUyLocal verificandoUy;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descripcion = request.getParameter("descripcion");
        String fechaStr = request.getParameter("fecha");
        String calificacionStr = request.getParameter("calificacion");
        String estadoStr = request.getParameter("estado");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        Calificacion calificacion;

        try {
            // Parse fecha
            fecha = formatter.parse(fechaStr);

            // Convertir a enum Calificacion
            calificacion = Calificacion.valueOf(calificacionStr);

            // El número se autogenera en el bean
            verificandoUy.agregarHecho(fecha, descripcion, calificacion);

            response.setContentType("text/html");
            response.setHeader("Refresh", "2;url=agregar.jsp");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Éxito</title></head><body>");
            out.println("<h1>Hecho agregado con éxito</h1>");
            out.println("<p>Redirigiendo a la página de agregar en 2 segundos...</p>");
            out.println("</body></html>");
            out.close();
        } catch (IllegalArgumentException e) {
            // Manejo de errores específicos para Calificacion
            response.setContentType("text/html");
            response.setHeader("Refresh", "2;url=agregar.jsp");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Error: Valor inválido para calificación</h1>");
            out.println("<p>Redirigiendo a la página de agregar en 2 segundos...</p>");
            out.println("</body></html>");
            out.close();
        } catch (ParseException e) {
            // Manejo de errores de formato de fecha
            response.setContentType("text/html");
            response.setHeader("Refresh", "2;url=agregar.jsp");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Error: Formato de fecha inválido</h1>");
            out.println("<p>Redirigiendo a la página de agregar en 2 segundos...</p>");
            out.println("</body></html>");
            out.close();
        } catch (Exception e) {
            // Manejo de errores generales
            response.setContentType("text/html");
            response.setHeader("Refresh", "2;url=agregar.jsp");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Error al agregar el hecho</h1>");
            out.println("<p>Redirigiendo a la página de agregar en 2 segundos...</p>");
            out.println("</body></html>");
            out.close();
            throw new RuntimeException(e);
        }
    }
}