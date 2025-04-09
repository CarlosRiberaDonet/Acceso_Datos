/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
    
    public Collection conectarBD() throws Exception{
        
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
        Collection col = conectarBD();
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
        Node nodo = res.getContentAsDOM();
        
        NodeList hijo = nodo.getChildNodes();
        NodeList datosEmpleado = hijo.item(0).getChildNodes();
        System.out.println("Datos empleado: " + datosEmpleado);
        // llamar a leerDoomempleado y pasarle datosEmpleado
        }
        // Parsear y construir los objetos Empleado
        
        return empleadosList; 
    }
}
