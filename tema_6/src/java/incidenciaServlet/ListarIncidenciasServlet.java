/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidenciaServlet;

import controllers.IncidenciaEJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Incidencias;

/**
 *
 * @author Carlos Ribera
 */
@WebServlet(name = "ListarIncidenciasServlet", urlPatterns = {"/ListarIncidenciasServlet"})
public class ListarIncidenciasServlet extends HttpServlet {

    @EJB
    IncidenciaEJB ic;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListarIncidenciasServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListarIncidenciasServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<Incidencias> incidenciasList = ic.getIncidenciasList();

        out.println("<html><body>");
        out.println("<h2>Lista de Incidencias</h2>");
        if (incidenciasList != null && !incidenciasList.isEmpty()) {
            for (Incidencias i : incidenciasList) {
                out.println("<hr>");
                out.println("<p>Id: " + i.getId() + "</p>");
                out.println("<p>Fecha: " + i.getFecha() + "</p>");
                out.println("<p>Empleado Origen: " + i.getIdEmpleadoOrigen().getNombreCompleto() + "</p>");
                out.println("<p>Empleado Destino: " + i.getIdEmpleadoDestino().getNombreCompleto() + "</p>");
                out.println("<p>Detalle: " + i.getDetalle() + "</p>");
                out.println("<p>Tipo: " + i.getTipo() + "</p>");
            }
        } else {
            out.println("<p>No se encontró ninguna incidencia.</p>");
        }
        out.println("<br><a href='menu.jsp'>Volver al menú</a>");
        out.println("</body></html>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
