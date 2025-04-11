/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import static controllers.EmpleadoController.leerDomEmpleado;
import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

/**
 *
 * @author Carlos Ribera
 */
public class GestorXML {
    private final String DRIVER = "org.exist.xmldb.DatabaseImpl";
    private final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private final String USUARIO = "admin";
    private final String PASSWORD = "admin";
    private final String COLECCION = "/db/sistema";
    
    public Collection conexionBD() throws Exception{
        
        // Cargo el driver
        Class<?> cl = Class.forName(DRIVER);
        // Creo instancia de Database
        Database dataBase = (Database) cl.getDeclaredConstructor().newInstance();
        // Registrola la BD en DatabaseManager
        DatabaseManager.registerDatabase(dataBase);
        // Obtengo la colección
        Collection col = DatabaseManager.getCollection(URI + COLECCION, USUARIO, PASSWORD);   
        // Devuelvo la colección
        return col;
    }
    
    public List<Empleado> obtenerEmpleados() throws Exception{
        
         // Creo una lista vacía para almacenar los empleados del XML
        List<Empleado> empleadosList = new ArrayList<>();
        
        // Obtener el servicio XQueryService desde la colección, versión 1.
        // Este servicio nos permitirá ejecutar consultas XQuery
        Collection col = conexionBD();
        // Obtener XQueryService
        XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
        
        // Defino la consulta XQuery para recuperar todos los nodos <empleado>
        String consulta = "for $e in doc(\"empleados.xml\")//empleado return $e";
        
        // Ejecuto la consulta y guardo el resultado
        ResourceSet resultados = servicio.query(consulta);
        // Recorrer los resultados
        ResourceIterator iterator = resultados.getIterator();
        while(iterator.hasMoreResources()){
        XMLResource res = (XMLResource) iterator.nextResource();
        // Obtengo el nodo raíz
        Node empleadoNode = res.getContentAsDOM();
        
        NodeList datosEmpleado = empleadoNode.getChildNodes();
        // llamar a leerDoomempleado y pasarle datosEmpleado
        Empleado empleado = EmpleadoController.leerDomEmpleado(datosEmpleado);
        empleadosList.add(empleado);
        } 
        return empleadosList; 
    }
    
    public void insertarEmpleado(Empleado empleado) throws XMLDBException, Exception{
        
        try{
            // Me conecto a la BS
            Collection col = conexionBD();

            // Obtengo el servicio XQueryService para ejecutar consultas
            XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");

            // Construyo el bloque XML que representa el nuevo empleado
            String xmlEmpleado = "<empleado>" +
                                    "<usuario>" + empleado.getNombreUsuario() + "</usuario>" +
                                    "<password>" + empleado.getPassword() + "</password>" +
                                    "<nombre>" + empleado.getNombre() + "</nombre>" +
                                    "<apellidos>" + empleado.getApellidos() + "</apellidos>" +
                                    "<direccion>" + empleado.getDireccion() + "</direccion>" +
                                    "<telefono>" + empleado.getTelefono() + "</telefono>" +
                                "</empleado>";

            // Construyo la consulta XQuery para inserción
            String consulta = "update insert " + xmlEmpleado + "  into doc(\"empleados.xml\")/empleados"; // Inserto el bloque <empleado> en el nodo raíz <empleados>

           // Ejecuto la consulta
           servicio.query(consulta);
           col.close(); // Libero recursos
        } catch(Exception e){
            System.out.println("Error al insertar el empleado: " + e.getMessage());
        }  
    }
    
    public boolean validarEntrada(String nombreUsuario, String password) throws Exception{
        
        // Me conecto a la BD
        Collection col = conexionBD();
        
        // Obtengo el servicio XQuery para ejecutar consultas
        XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
        
        // Construyo la consulta
        String xmlLogin =   "for $e in doc(\"empleados.xml\")//empleado " +
                            "where $e/usuario = \"" + nombreUsuario + "\" " +
                            "and $e/password = \"" + password + "\" " +
                            "return $e";

        // Ejecuto la consulta
        ResourceSet resultado = servicio.query(xmlLogin);
        ResourceIterator iterator = resultado.getIterator();
        
        // Si hay al menos un resultado, el usuario y password conciden
        if(iterator.hasMoreResources()){
            return true;
        }
        
        // Si no hay resultado, devuelvo false
        return false;
    }
    
    public Empleado modificarEmpleado(String nombre, String apellidos) throws Exception{
        
        Collection col = conexionBD();
        
        XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
        
        String xmlBuscarEmpleado =   "for $e in doc(\"empleados.xml\")//empleado " + 
                                "where $e/nombre = \"" + nombre + "\" " +
                                "and $e/apellidos = \"" + apellidos + "\" " +
                                 "return $e";
        
        ResourceSet resultado = servicio.query(xmlBuscarEmpleado);
        ResourceIterator iterator = resultado.getIterator();
        
        // Si hay al menos un resultado
        if(iterator.hasMoreResources()){
            // Obtengo el primer recurso devuelto
            XMLResource res = (XMLResource) iterator.nextResource();
            
            // Extraigo el contenido del nodo como estructura DOM
            Node nodo = res.getContentAsDOM(); // representa el nodo <empleado>
            
            // Obtengo los nodos hijos del nodo <empleado>
            NodeList datosEmpleado = nodo.getChildNodes();
            
            // Llamo al método que convierte el NodeList en un objeto de tipo Empleado
            Empleado empleado = leerDomEmpleado(datosEmpleado);
            
            // Devuelvo el objeto Empleado creado
            return empleado;
        }
        
       return null; // Si no encuentra el empleado, devuelvo null
    }
}
