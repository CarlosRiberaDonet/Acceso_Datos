/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadoServlet;

import controllers.EmpleadoEJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Empleados;
import utils.JPAUtil;

/**
 *
 * @author Carlos Ribera
 */
@WebServlet(name = "ActualizarEmpleadoServlet", urlPatterns = {"/ActualizarEmpleadoServlet"})
public class ActualizarEmpleadoServlet extends HttpServlet {

    @EJB
    EmpleadoEJB ec;
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
            out.println("<title>Servlet ActualizarEmpleadoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarEmpleadoServlet at " + request.getContextPath() + "</h1>");
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
        
        // Recojo los datos introducidos por el USR
        String idStr = request.getParameter("idUsuario");        
        String nuevoNombreUsuario = request.getParameter("nuevoNombreUsuario").toLowerCase();
        String nuevaContrasena = request.getParameter("nuevaContrasena");
        String nuevoNombreCompleto = request.getParameter("nuevoNombreCompleto").toLowerCase();
        String nuevoTelefono = request.getParameter("nuevoTelefono");
        
        // Valido los campos
        if (!JPAUtil.esIdValido(idStr) ||
            !JPAUtil.esTextoValido(nuevoNombreUsuario) ||
            !JPAUtil.esTextoValido(nuevaContrasena) ||
            !JPAUtil.esTextoValido(nuevoNombreCompleto) ||
            !JPAUtil.esTelefonoValido(nuevoTelefono)) {

            out.println("<html><body>");
            out.println("<h2>Hay campos inválidos o incompletos</h2>");
            out.println("<a href='nuevoEmpleado.jsp'>Volver</a>");
            out.println("</body></html>");
            return;
        }
        
        // Parseo el id
        int id = Integer.parseInt(idStr);
        
        // Obtengo el objeto de tipo Empleados a partir su id
        Empleados empleadoOriginal = ec.getEmpleadoById(id);
        
        // Si el empleado existe
        if(empleadoOriginal != null){
            
            // Modifico los datos del objeto original
            empleadoOriginal.setNombreUsuario(nuevoNombreUsuario);
            empleadoOriginal.setContrasena(nuevaContrasena);
            empleadoOriginal.setNombreCompleto(nuevoNombreCompleto);
            empleadoOriginal.setTelefono(nuevoTelefono);

            
            // Llamo al método modificarEmpleado y guardo los datos en la BD
            ec.modificarEmpleado(empleadoOriginal);
            out.println("<html><body>");
            out.println("<h2>Empleado actualizado correctamente.</h2>");
            out.println("<a href='index.jsp'>Volver al menú</a>");
            out.println("</body></html>");
            
        }  else{
            out.println("<html><body>");
            out.println("<h2>Empleado no encontrado</h2>");
            out.println("<a href='index.jsp'>Volver al menú</a>");
            out.println("</body></html>");
        }
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
