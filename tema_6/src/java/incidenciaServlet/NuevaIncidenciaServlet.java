/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidenciaServlet;

import controllers.EmpleadoEJB;
import controllers.IncidenciaEJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Empleados;
import modelos.Incidencias;

/**
 *
 * @author Carlos Ribera
 */
@WebServlet(name = "NuevaIncidenciaServlet", urlPatterns = {"/NuevaIncidenciaServlet"})
public class NuevaIncidenciaServlet extends HttpServlet {

    @EJB
    IncidenciaEJB ic;
    
    @EJB
    EmpleadoEJB ec;

    public NuevaIncidenciaServlet() {
    }
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
            out.println("<title>Servlet NuevaIncidenciaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevaIncidenciaServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Obtengo parámetros
        String fechaStr = request.getParameter("fecha");
        Date fecha;
        String origenStr = request.getParameter("empleadoOrigen").toLowerCase();
        String destinoStr = request.getParameter("empleadoDestino").toLowerCase();
        String detalle = request.getParameter("detalle");
        String tipoStr = request.getParameter("tipo").toUpperCase();

        // Valido campos vacíos
        if (fechaStr == null || fechaStr.isEmpty() ||
            origenStr == null || origenStr.isEmpty() ||
            destinoStr == null || destinoStr.isEmpty() ||
            detalle == null || detalle.isEmpty() ||
            tipoStr == null || tipoStr.isEmpty()) {

            out.println("<html><body>");
            out.println("<h2>Todos los campos son obligatorios.</h2>");
            out.println("<a href='crearIncidencia.jsp'>Volver</a>");
            out.println("</body></html>");
            return;
        }

        // Valido fecha
        try {
            LocalDateTime fechaLocal = LocalDateTime.parse(fechaStr);
            fecha = Date.from(fechaLocal.atZone(ZoneId.systemDefault()).toInstant());
        } catch (IllegalArgumentException e) {
            out.println("<html><body>");
            out.println("<h2>Formato de fecha no válido.</h2>");
            out.println("<a href='crearIncidencia.jsp'>Volver</a>");
            out.println("</body></html>");
            return;
        }

        // Valido empleados
        Empleados origen = ec.checkNombreCompleto(origenStr);      
        if (origen == null) {
            out.println("<html><body>");
            out.println("<h2>Empleado origen no válido.</h2>");
            out.println("<a href='crearIncidencia.jsp'>Volver</a>");
            out.println("</body></html>");
            return;
        }
        Empleados destino = ec.checkNombreCompleto(destinoStr);
        if(destino == null){
            out.println("<html><body>");
            out.println("<h2>Empleado destino no válido.</h2>");
            out.println("<a href='crearIncidencia.jsp'>Volver</a>");
            out.println("</body></html>");
        }

        // Valido tipo
        char tipo = tipoStr.charAt(0);
        if (tipo != 'N' && tipo != 'U') {
            out.println("<html><body>");
            out.println("<h2>El tipo debe ser 'N' (Normal) o 'U' (Urgente).</h2>");
            out.println("<a href='crearIncidencia.jsp'>Volver</a>");
            out.println("</body></html>");
            return;
        }

        // Si todo es válido, crear y persistir la incidencia
        Incidencias nueva = new Incidencias();
        nueva.setFecha(fecha);
        nueva.setIdEmpleadoOrigen(origen);
        nueva.setIdEmpleadoDestino(destino);
        nueva.setDetalle(detalle);
        nueva.setTipo(tipo);

        ic.crearIncidencia(nueva);

        out.println("<html><body>");
        out.println("<h2>Incidencia creada correctamente.</h2>");
        out.println("<a href='menu.jsp'>Volver al menú</a>");
        out.println("</body></html>"); 
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
