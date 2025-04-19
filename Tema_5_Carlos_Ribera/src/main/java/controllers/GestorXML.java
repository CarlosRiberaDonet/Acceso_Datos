/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.EmpleadoController;
import controllers.IncidenciaController;
import static controllers.EmpleadoController.leerDomEmpleado;
import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;
import modelos.Incidencia;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XMLResource;

import utils.Utils;

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
    
    public Collection conexionBD(){
        
        Collection col = null;
        
        try{
            // Cargo el driver
            Class<?> cl = Class.forName(DRIVER);
            // Creo instancia de Database
            Database dataBase = (Database) cl.getDeclaredConstructor().newInstance();
            // Registrola la BD en DatabaseManager
            DatabaseManager.registerDatabase(dataBase);
            // Obtengo la colección
            col = DatabaseManager.getCollection(URI + COLECCION, USUARIO, PASSWORD);
        } catch(XMLDBException e){
            System.out.println("No ha sido posible establecer la conexion con la BD: " + URI);
        } catch(Exception e){
            System.out.println("Error inseperado al conectar la BD: " + e.getMessage());
        } 
        // Devuelvo la colección
        return col;
    }   
    
    public void cerrarConexion(Collection col){
        
        if(col != null){
            try{
                col.close();
            }catch(XMLDBException e){
                System.out.println("Error al cerrar la conexion con la BD: " + e.getMessage());
            }
        }
    }    
    
     // METODOS PARA  LA CLASE EMPLEADO CONTROLLER
    public List<Empleado> obtenerListaEmpleados(){
        
        Collection col = null;
        
        // Creo una lista vacía para almacenar los empleados del XML
        List<Empleado> empleadosList = new ArrayList<>();
        
        try{
            // Obtener el servicio XQueryService desde la colección, versión 1.
            col = conexionBD();
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
        } catch(XMLDBException e){
            System.out.println("Error al intentar obtener la lista de empleados " + e.getMessage());
        } catch(Exception e){
            System.out.println("Error inesperado al intentar obtener la lista de empleados " + e.getMessage());
        } finally{
           cerrarConexion(col);
        }
        return empleadosList; 
    }
    
    public void insertarEmpleado(Empleado empleado){
        
        Collection col = null;
        
        try{
            // Me conecto a la BS
            col = conexionBD();

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
        } catch (XMLDBException e){
            System.out.println("Error al intentar agregar un nuevo empleado en la BD " + e.getMessage());
            
        } finally{
            cerrarConexion(col);
        }
    }
    
    public boolean validarEntrada(String nombreUsuario, String password){
        
        // Me conecto a la BD
        Collection col = null;
        
        try{
            col = conexionBD();
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
            // Si hay al menos un resultado, el usuario y password conciden. retorna true, si no, false
            return iterator.hasMoreResources();
        } catch(XMLDBException e){
            System.out.println("Error en el login: " + e.getMessage());
        } catch(Exception e){
            System.out.println("ERROR inesperado en el login: " + e.getMessage());
        }
        finally{
            cerrarConexion(col);
        }
        
        return false;
    }
    
    public Empleado obtenerEmpleado(String nombre, String apellidos){
        
        Collection col = null;
        
        try{
            col = conexionBD();
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
        } catch(XMLDBException e){
            System.out.println("Error al intentar obtener el empleado de la BD: " + e.getMessage());
        } finally{
            cerrarConexion(col);
        }
        
       return null; // Si no encuentra el empleado, devuelvo null
    }
    
    public void updateEmpleado(String nombreUsuario, String nodo, String nuevoValor){
        
        Collection col = null;
        try{
            col = conexionBD();
             XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
        
            String update = "for $e in doc(\"empleados.xml\")//empleado " +
                            "where $e/usuario = \"" + nombreUsuario + "\" " +
                            "return update value $e/" + nodo + " with \"" + nuevoValor + "\"";

            servicio.query(update);
        } catch(XMLDBException e){
            System.out.println("Error al actualizar los datos del empleado: " + e.getMessage());
        } finally{
            cerrarConexion(col);
        }    
    }
    
    public boolean deleteUser(String nombreUsuario){
        
        Collection col = null;
        try{
            
            col = conexionBD();
             XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
        
            // Verifico si el usuario existe
            String consulta = "for $e in doc(\"empleados.xml\")//empleado " +
                             "where $e/usuario = \"" + nombreUsuario + "\" " +
                             "return $e";

            ResourceSet resultado = servicio.query(consulta);
            ResourceIterator iterator = resultado.getIterator();

            if(iterator.hasMoreResources()){
                // Si existe, lo elimino
                if(Utils.solicitaConfirmacion("¿Seguro que desea eliminar el usuario " + nombreUsuario + " ?")){
                    String delete = "update delete " +
                                "for $e in doc(\"empleados.xml\")//empleado " +
                                "where $e/usuario = \"" + nombreUsuario + "\" " +
                                "return $e";

                    servicio.query(delete);
                    return true;
                }        
            }  
        } catch(XMLDBException e){
            System.out.println("Error al eliminar el empleado de la BD: " + e.getMessage());
        } finally{
            cerrarConexion(col);
        }  
       
        return false;  
    }
    
    
    // METODOS PARA  LA CLASE INCIDENCIA CONTROLLER
    
    public Incidencia getIncidenciaById(int id){
        
        // Establezco la conexion con la BD
        Collection col = null;
        
        Incidencia incidencia = null;
        
        try{
            col = conexionBD();
             XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
        
            // Construyo la consulta
            String consulta = "for $i in doc(\"incidencias.xml\")//incidencia " +
                               "where $i/id = \"" + id + "\" " +
                               "return $i";

            // Ejecuto la consulta y proceso
            ResourceSet resultado = servicio.query(consulta);
            ResourceIterator iterator = resultado.getIterator();
        
            if(iterator.hasMoreResources()){
                XMLResource rs = (XMLResource) iterator.nextResource();
                Node nodo = rs.getContentAsDOM();
                
                NodeList datosIncidencia = nodo.getChildNodes();
                incidencia = IncidenciaController.leerDomIncidencia(datosIncidencia);
            }  
        }  catch(XMLDBException e){
            System.out.println("Error al obtener la incidencia de la BD: " + e.getMessage());
        } finally{
            cerrarConexion(col);
        }    
         
        return incidencia;
    }
    
    public List<Incidencia> getIncidenciasList(){
        
        List<Incidencia> incidenciasList = new ArrayList<>();
        Collection col = null;
        
        try{
            col = conexionBD();
            // Obtengo el sercivio XQuery de consultas
            XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");

            // Defino la consulta
            String consulta = "for $i in doc(\"incidencias.xml\")//incidencia return $i";

           // Ejecuto la consulta
           ResourceSet resultado = servicio.query(consulta);

           // Recorro todos los resultados de la consulta uno a uno
           ResourceIterator iterator = resultado.getIterator();

           while(iterator.hasMoreResources()){
               // Obtengo el recurso actual
               XMLResource rs = (XMLResource) iterator.nextResource();

               // Obtengo el contenido del recurso en forma de nodo DOM
               Node nodo = rs.getContentAsDOM();

               // Obtengo los hijos del nodo raíz
               NodeList datosIncidenia = nodo.getChildNodes();

               // Llamo a un método que transforma los datos XML en un objeto de tipo incidencia
               Incidencia incidencia = IncidenciaController.leerDomIncidencia(datosIncidenia);
               incidenciasList.add(incidencia);
           }
        } catch(XMLDBException e){
            System.out.println("Error al obtener la lista de incidencias: " + e.getMessage());
        } finally{
            cerrarConexion(col);
        }   
 
        return incidenciasList;
    }
    
    public int getMaxIdIncidencia(){
        
        Collection col = null;
        int id = 0;
        
        try{
            col = conexionBD();
            XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
            
            // Consulta para obtener el id máximo de las incidencias
            String obtenerId = "max(for $i in doc(\"incidencias.xml\")//incidencia return xs:int($i/id))";
            
            ResourceSet resultado = servicio.query(obtenerId);
            ResourceIterator iterator = resultado.getIterator();
            
            // Si hay resultados, lo obtengo
            if(iterator.hasMoreResources()){
                Resource rs = iterator.nextResource();
                
                // El contenido viene en formato String y lo convierto a int
                String idString = (String) rs.getContent();
                id = Integer.parseInt(idString);
            }
            
        } catch(XMLDBException e){          
        } finally{
            cerrarConexion(col);
        }
        
        return id; // Devuelvo el valor del último id encontrado
    }
    
    public void insertarIncidencia(Incidencia incidencia){
        
        Collection col = null;
        
        try{
            col = conexionBD();
            XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
            
             // Construyo el bloque XML que representa la nueva incidencia
            String xmlIncidencia = "<incidencia>" +
                                        "<id>" + incidencia.getId() + "</id>" +
                                        "<origen>" + incidencia.getEmpleadoOrigen().getNombreUsuario() + "</origen>" +
                                        "<destino>" + incidencia.getEmpleadoDestino().getNombreUsuario() + "</destino>" +
                                        "<tipo>" + incidencia.getTipoIncidencia() + "</tipo>" +
                                        "<detalle>" + incidencia.getDetalleIncidencia() + "</detalle>" +
                                        "<fechahora>" + incidencia.getFechaHora() + "</fechahora>" +
                                    "</incidencia>";
            
            // Construyo la consulta XQuery para la inserción de la incidencia en el XML
            String consulta = "update insert " + xmlIncidencia + " into doc (\"incidencias.xml\")/incidencias";
            
            // Ejecuto la consulta
            servicio.query(consulta);
            System.out.println("Incidencia insertada correctamente");
        } catch(XMLDBException e){
            System.out.println("Error al insertar la incidencia: " + e.getMessage());      
        } finally {
            cerrarConexion(col);
        }
    }

    // Si recibe true, obtendra las incidencias creadas, si recibe false, las destinadas
    public List<Incidencia> getIncicidenciasEmpleado(String nombreUsuario, boolean incidenciaCreada){
        
        List<Incidencia> incidenciasList = new ArrayList<>();
        String consulta = "";
        
        Collection col = null;
        
        try{
            col = conexionBD();
            XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
            
            // Construyo consulta para obtener las incidencias creadas por un usuario
            if(incidenciaCreada){
                consulta = "for $i in doc(\"incidencias.xml\")//incidencia " +
                               "where $i/origen = \"" + nombreUsuario + "\" " +
                               "return $i";
            }
            // Construyo consulta para obtener las incidencias destinadas a un usuario
            else if(!incidenciaCreada){
                consulta = "for $i in doc(\"incidencias.xml\")//incidencia " +
                               "where $i/destino = \"" + nombreUsuario + "\" " +
                               "return $i";
            }
            
            // Ejecuta la consulta XQuery previamente construida y guarda los resultados
            ResourceSet resultado = servicio.query(consulta);
            // Crea un iterador para recorrer todos los resultados obtenidos
            ResourceIterator iterator = resultado.getIterator();
            // Mientras haya más recursos (nodos <incidencia>) en el resultado...
            while(iterator.hasMoreResources() ){
                // Obtiene el siguiente recurso XML que representa una incidencia
                XMLResource rs = (XMLResource) iterator.nextResource();
                 // Extrae el contenido del recurso como un nodo DOM
                Node nodo = rs.getContentAsDOM();
                // Obtiene todos los nodos hijos de la incidencia (id, origen, destino, etc.)
                NodeList datosIncidencia = nodo.getChildNodes();
                // Transforma los nodos XML en un objeto Java de tipo Incidencia
                Incidencia incidencia = IncidenciaController.leerDomIncidencia(datosIncidencia);
                // Añade el objeto Incidencia a la lista de incidencias
                incidenciasList.add(incidencia);
            }
            
        } catch(XMLDBException e){
            System.out.println("Error al obtener la lista de incidencias" + e.getMessage());
        } finally{
            cerrarConexion(col);
        }
        
        return incidenciasList;
    }
}
