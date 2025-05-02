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

/**
 *
 * @author Carlos Ribera
 */
@WebServlet(name = "ServletEJB", urlPatterns = {"/ServletEJB"})
public class ServletEJB extends HttpServlet {
    
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
            out.println("<title>Servlet InsertarEmpleadoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmpleadoServlet at " + request.getContextPath() + "</h1>");
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
        
        // Recoge los datos del formulario
        String usuario = request.getParameter("usuario").toLowerCase();
        String contrasena = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre").toLowerCase();
        String telefono = request.getParameter("telefono");
        
        Empleados empleado = ec.checkNombreUsuario(usuario);
        
        // Si el empleado ya existe
        if(empleado != null){
            // Respuesta al cliente
            out.println("<html><body>");
            out.println("<h3>El nombre de usuario introducido ya existe, elija otro.</h3>");
            out.println("<a href='index.jsp'>Volver al menú</a>");
            out.println("</body></html>");
        } else{
            // Asigno al objeto Empleados los datos introducidos por el USR
            empleado = new Empleados();
            empleado.setNombreUsuario(usuario);
            empleado.setContrasena(contrasena);
            empleado.setNombreCompleto(nombre);
            empleado.setTelefono(telefono);

            // Paso el objeto Empleado al método que se engarga de insertarlo en la BD
            ec.insertarEmpleado(empleado);

            // Respuesta al cliente
            out.println("<html><body>");
            out.println("<h3>Empleado recibido correctamente.</h3>");
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
